package com.amolli.oyeongshop.ver2.product.controller;

import com.amolli.oyeongshop.ver2.board.dto.ReviewResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.service.ReviewService;
import com.amolli.oyeongshop.ver2.product.dto.*;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.service.ProductImageService;
import com.amolli.oyeongshop.ver2.product.service.ProductOptionService;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import com.amolli.oyeongshop.ver2.s3.AwsS3Service;
import com.amolli.oyeongshop.ver2.s3.AwsS3ServiceProduct;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
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
        List<ProductResponse> productList = productService.findProduct100(sort);
        model.addAttribute("productList", productList);
        model.addAttribute("prodCategory", "best100");
        return "product/product-list";
    }

    // [상품 목록] 신상품
    @GetMapping("/list/newArrivals/regdate")
    public String newProduct(Model model) {
        List<ProductResponse> productList = productService.findByNewProdJPQL();
        model.addAttribute("productList", productList);
        return "product/product-list";
    }

    // [상품 목록] 세일 중인 상품
    @GetMapping("/list/sale/{sort}")
    public String saleProduct(@PathVariable String sort, Model model) {
        List<ProductResponse> productList = productService.findBySaleProd(sort);
        model.addAttribute("productList", productList);
        model.addAttribute("prodCategory", "sale");
        return "product/product-list";
    }

    // [상품 목록] 모든 상품을 조회 및 정렬
    @GetMapping("/list/all/{sort}")
    public String productList(@PathVariable String sort, Model model) {
        List<ProductResponse> productList = productService.findByProdCategoryJPQL(sort);
        model.addAttribute("productList", productList);
        model.addAttribute("prodCategory", "all");
        return "product/product-list";
    }

    // [상품 목록] 상품 리스트를 카테고리 별로 조회 및 정렬
    @GetMapping("/list/{prodCategory}/{sort}")
    public String getProductsByCategory(@PathVariable String prodCategory, @PathVariable String sort, Model model) {
        List<ProductResponse> productList = productService.findByProdCategoryJPQL(prodCategory, sort);

        // 모델에 데이터 추가
        model.addAttribute("category", prodCategory);
        model.addAttribute("productList", productList);

        System.out.println(prodCategory);
        // 뷰 이름 반환 (타임리프 템플릿 이름)
        return "product/product-list";
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
        List<ProductResponse> productList = productService.findProductPaged(currentPage, itemsPerPage);
        model.addAttribute("productList", productList);

        return "product/product-management";
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

        Product product = productService.findById(prodId);

        // productId를 사용하여 필요한 데이터를 데이터베이스에서 가져온다.
        ModelAndView mav = new ModelAndView("product/product-detail");
        // Thymeleaf에 데이터를 전달
        mav.addObject(productService.findById(prodId));

        // - 나영 - 리뷰 List 불러오기
        List<Review> reviews = reviewService.findByProdId(prodId);
        List<ReviewResponseDTO> reviewdto = reviews.stream().map(ReviewResponseDTO::from).collect(Collectors.toList());
        model.addAttribute("reviewdto", reviewdto);

        System.out.println("reviewdto");

        // - 정환 - 상품 이미지 List 불러오기
        List<Product> products = productService.findByProdId(prodId);
        List<ProductResponseDTO> productImageResponseDTO = products.stream().map(ProductResponseDTO::from).collect(Collectors.toList());
        model.addAttribute("productImageResponseDTO", productImageResponseDTO);

        return mav;
    }

    // [상품 상세 정보 수정] 화면으로 랜더링
    @GetMapping("/edit/{prodId}")
    public String initUpdateOwnerForm(@PathVariable Long prodId, Model model) {
        model.addAttribute("product",productService.findById(prodId));
        model.addAttribute("productOption",productOptionService.findByProduct_ProdId(prodId));
        return "product/product-register";
    }

    // [상품 상세 정보 수정] 상품 수정 화면의 입력 데이터 보내기
    @PostMapping("/edit/{prodId}")
    public String initUpdateForm(@Validated Product product, @Validated ProductOptionDTO productOptionDTO, @PathVariable Long prodId) {
        product.setProdId(prodId);
        productService.saveProduct(product);
        productOptionService.saveProductOption(productOptionDTO);
        System.out.println("aaaaaaaaaaaaaa");
        return "product/product-register-detail";
    }

    // [상품 관리] 상품판매구분 컬럼 YES, NO 업데이트
    @ResponseBody
    @PostMapping("/editor/{prodId}")
    public void UpdataSalesStatusYesNo(@PathVariable Long prodId) {
        productService.UpdataSalesStatusYesNo(prodId);
    }

    // [상품 등록] 화면으로 렌더링
    @GetMapping("/register")
    public String productRegister(Model model) {
        model.addAttribute("product", Product.builder().build());
        model.addAttribute("productOption", ProductOption.builder().build());
        return "product/product-register";
    }

    // [상품 등록] 상품 기본 정보, 상품 옵션, 이미지 등록
    @PostMapping("/register/{prodId}")
    public String processCreationForm(@PathVariable Long prodId, @RequestParam(value = "image1", required = false) List<MultipartFile> files, Product product) {
        // productService를 사용하여 상품 기본 정보를 등록
        productService.saveProduct(product);
        System.out.println("상품 상세 정보 수정 완료");

        // 등록한 이미지를 저장하기 위한 프로세스
        List<String> imagepath = null;

        // 멀티파트파일->S3에 업로드 하고 imageUrls 리스트로 받아옴
        if (!ObjectUtils.isEmpty(files) && !files.get(0).getOriginalFilename().equals("")) {
            imagepath = awsS3Service.uploadS3ForProduct(files);

            System.out.println("files : "+ files);
        }

        // imageUrls를 받아서 DB에 업로드
        productService.uploadDBForProduct(imagepath, product);

        System.out.println("이미지 파일 업로드 완료");
        return "redirect:/product/management";
    }

    // [상품 및 상품 옵션 등록] - POST 요청을 처리하여 상품과 상품 옵션을 등록하는 메서드
    @PostMapping("/register")
    public String processCreationForm(@Valid Product product, @Valid ProductOptionDTO productOptionDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "product/product-register";
        } else {
            // productService를 사용하여 상품을 등록
            Product savedProduct=productService.saveProduct(product);
            System.out.println("상품 기본 정보 등록 완료 = savedProduct = " + savedProduct.getProdId());

            // productOptionService를 사용하여 상품 옵션을 등록
            productOptionDTO.setProdId(savedProduct.getProdId());
            productOptionService.saveProductOption(productOptionDTO);

            System.out.println("상품 옵션 등록 완료");

            System.out.println(redirectAttributes.addAttribute("prodId", product.getProdId()));
            return "product/product-register-detail";
        }
    }
}

