package com.amolli.oyeongshop.ver2.product.controller;

import com.amolli.oyeongshop.ver2.board.dto.ReviewResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.service.ReviewService;
import com.amolli.oyeongshop.ver2.product.dto.ProductImageListResponseDTO;
import com.amolli.oyeongshop.ver2.product.dto.ProductOptionResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.service.ProductOptionService;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import com.amolli.oyeongshop.ver2.s3.AwsS3Service;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductOptionService productOptionService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final AwsS3Service awsS3Service;


    // [사이드 바] side-nav-for-user.html 에서 a태그 클릭 시 category 정보를 전달해주는 컨트롤러
    @RequestMapping(value = "/list/{prodCategory}", method = RequestMethod.GET)
    public String productCategoryFromViewToList(@PathVariable String prodCategory, Model model) {
        // category 값을 모델에 추가
        model.addAttribute("category", prodCategory);
        // product-list.html 뷰로 이동
        return "product/product-list";
    }

    // [상품 목록] Best 100
    @GetMapping("/list/best100/{sort}")
    public String bestProduct(@PathVariable String sort, Model model) {
        List<ProductOptionResponse> productList = productService.findProduct100(sort);
        model.addAttribute("productList", productList);
        model.addAttribute("prodCategory", "best100");
        return "product/product-list";
    }

    // [상품 목록] 신상품
    @GetMapping("/list/newArrivals/regdate")
    public String newProduct(Model model) {
        List<ProductOptionResponse> productList = productService.findByNewProdJPQL();
        model.addAttribute("productList", productList);
        return "product/product-list";
    }

    // [상품 목록] 세일 중인 상품
    @GetMapping("/list/sale/{sort}")
    public String saleProduct(@PathVariable String sort, Model model) {
        List<ProductOptionResponse> productList = productService.findBySaleProd(sort);
        model.addAttribute("productList", productList);
        model.addAttribute("prodCategory", "sale");
        return "product/product-list";
    }

    // [상품 목록] 모든 상품을 조회 및 정렬
    @GetMapping("/list/all/{sort}")
    public String productList(@PathVariable String sort, Model model) {
        List<ProductOptionResponse> productList = productService.findByProdCategoryJPQL(sort);
        model.addAttribute("productList", productList);
        model.addAttribute("prodCategory", "all");
        return "product/product-list";
    }

    // [상품 목록] 상품 리스트를 카테고리 별로 조회 및 정렬
    @GetMapping("/list/{prodCategory}/{sort}")
    public String getProductsByCategory(@PathVariable String prodCategory, @PathVariable String sort, Model model) {
        List<ProductOptionResponse> productList = productService.findByProdCategoryJPQL(prodCategory, sort);

        // 모델에 데이터 추가
        model.addAttribute("category", prodCategory);
        model.addAttribute("productList", productList);

        System.out.println(prodCategory);
        // 뷰 이름 반환 (타임리프 템플릿 이름)
        return "product/product-list";
    }

    // [상품 상세 정보] 선택한 상품의 상세 정보 보기
    @GetMapping("/detail/{prodId}")
    public ModelAndView productDetail(@PathVariable Long prodId, Model model, @AuthenticationPrincipal PrincipalDetails details) {
        // - 나영 - @AuthenticationPrincipal PrincipalDetails null 처리 후 찜id view에 넘겨주기
        if (details != null) {
            // 찜여부 확인하기
            Long wishlistId = userService.findWishList(prodId, details);
            model.addAttribute("wishListId", wishlistId);
            String userId = details.getUser().getUserId();
            model.addAttribute("userId", userId);
        }

        // productId를 사용하여 필요한 데이터를 데이터베이스에서 가져온다.
        ModelAndView mav = new ModelAndView("product/product-detail");
        // Thymeleaf에 데이터를 전달
        mav.addObject(productService.findById(prodId));

        // - 나영 - 리뷰 List 불러오기
        List<Review> reviews = reviewService.findByProdId(prodId);
        List<ReviewResponseDTO> reviewdto = reviews.stream().map(ReviewResponseDTO::from).collect(Collectors.toList());
        model.addAttribute("reviewdto", reviewdto);

        System.out.println("reviewdto");

        // 상품 이미지 List 불러오기
        List<Product> products = productService.findByProdId(prodId);
        List<ProductImageListResponseDTO> productImageResponseDTO = products.stream().map(ProductImageListResponseDTO::from).collect(Collectors.toList());
        model.addAttribute("productImageResponseDTO", productImageResponseDTO);

        // 상품 옵션 불러오기
        Product product1 = products.get(0);
        List<ProductOption> productOptionList =  product1.getProductOptions();

        // set을 사용하여 옵션의 중복 제거
        Set<String> colorSet = new TreeSet<>();
        Set<String> sizeSet = new TreeSet<>();

        for (ProductOption po : productOptionList){
            colorSet.add( po.getProdOptColor());
            sizeSet.add(po.getProdOptSize());
        }
        System.out.println("colorSet = " + colorSet);
        System.out.println("sizeSet = " + sizeSet);

        // 색상과 사이즈 저장
        model.addAttribute("productColorSet", colorSet);
        model.addAttribute("productSizeSet", sizeSet);
        System.out.println("옵션 불러오기");

        return mav;
    }

    // [상품 목록] 상품 관리 리스트 for Paging Navigation
    @GetMapping("/management")
    public String productManagement(Model model, @RequestParam(name = "page", defaultValue = "1") int currentPage) {
        // 페이지당 항목 수
        int itemsPerPage = 10;
        // 전체 상품 수
        int totalItems = productService.getTotalProductCount();
        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        // 여기에서 currentPage를 모델에 추가
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        // 현재 페이지의 상품 목록 조회 (페이징된 결과)
        List<ProductOptionResponse> productList = productService.findProductPaged(currentPage, itemsPerPage);
        model.addAttribute("productList", productList);

        return "product/product-management";
    }
}

