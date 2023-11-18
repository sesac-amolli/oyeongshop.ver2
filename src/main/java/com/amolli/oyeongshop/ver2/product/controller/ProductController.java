package com.amolli.oyeongshop.ver2.product.controller;

import com.amolli.oyeongshop.ver2.board.dto.ReviewResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.service.ReviewService;
import com.amolli.oyeongshop.ver2.product.dto.ProductDTO;
import com.amolli.oyeongshop.ver2.product.dto.ProductResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.service.ProductOptionService;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import com.amolli.oyeongshop.ver2.s3.AwsS3ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    private final AwsS3ServiceProduct awsS3ServiceProduct;

    // side-nav-for-user.html 에서 a태그 클릭 시 category 정보를 전달해주는 컨트롤러
    @RequestMapping(value="/list/{prodCategory}", method=RequestMethod.GET)
    public String productCategoryFromViewToList(@PathVariable String prodCategory, Model model) {
        // category 값을 모델에 추가
        model.addAttribute("category", prodCategory);
        // product-list.html 뷰로 이동
        return "product/product-list";
    }

    // [상품 목록] - 모든 상품을 조회 및 정렬
    @GetMapping("/list/all/{sort}")
    public String productList(@PathVariable String sort, Model model) {
        //todo : 익숙해 지기
        List<ProductResponse> productList = productService.findByProdCategoryJPQL(sort);
        model.addAttribute("productList", productList);
        model.addAttribute("prodCategory", "all");
        return "product/product-list";
    }

    // [상품 목록] - 상품 리스트를 카테고리 별로 조회 및 정렬
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

    // [상품 목록] - 상품 관리 리스트 for Paging Navigation
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

    // [상품 상세 정보] - 선택한 상품 보기
    @GetMapping("/detail/{prodId}")
    public ModelAndView productDetail(@PathVariable Long prodId, Model model) {
        Product product = productService.findById(prodId);

        // 중복 옵션 제거
        product = productService.removeDuplicateOptions(product);
//        product = productService.removeDuplicateSizes(product);

        //productId를 사용하여 필요한 데이터를 데이터베이스에서 가져온다.
        ModelAndView mav = new ModelAndView("product/product-detail");
        // Thymeleaf에 데이터를 전달
        mav.addObject(productService.findById(prodId));

        // 리뷰 List 불러오기
        List<Review> reviews = reviewService.findByProdId(prodId);

        List<ReviewResponseDTO> reviewdto = reviews.stream().map(ReviewResponseDTO::from).collect(Collectors.toList());

        model.addAttribute("reviewdto", reviewdto);

        return mav;
    }

    // [상품 상세 정보 수정] - 화면으로 랜더링
    @GetMapping("/edit/{prodId}")
    public String initUpdateOwnerForm(@PathVariable Long prodId, Model model) {
        model.addAttribute(productService.findById(prodId));
        System.out.println("요 맞제?");
        model.addAttribute(productOptionService.findByProduct_ProdId(prodId));
        return "/product/product-register";
    }
    
    // [상품 상세 정보 수정] - 상품 수정 화면의 입력 데이터 보내기
    @PostMapping("/edit/{prodId}")
    public String initUpdateForm(@Validated Product product, @Validated ProductOption productOption, @PathVariable Long prodId) {
        product.setProdId(prodId);
        Product savedProduct = productService.saveProduct(product);
        ProductOption savedProductOption = productOptionService.saveProductOption(productOption);
        return "redirect:/product/management";
    }

    // [상품 관리] - 상품판매구분 컬럼 YES, NO 업데이트
    @ResponseBody
    @PostMapping("/editor/{prodId}")
    public void UpdataSalesStatusYesNo(@PathVariable Long prodId) {
        productService.UpdataSalesStatusYesNo(prodId);
    }
    // [상품 옵션 등록] - 상품옵션 ProdId 업데이트
//    @ResponseBody
//    @PostMapping("/register/{prodId}")
//    public void UpdataProdId(@PathVariable Long prodId) {
//        productOptionService.UpdataProdId(prodId);
//    }

    //    @GetMapping("/detail/edit")
//    public String productDetailEdit( ) {
//        return "/product/product-detail-edit";
//    }

    //    @ResponseBody
//    @PostMapping(value = "/review-write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String uploadFile(@RequestParam("image1") List<MultipartFile> file, ReviewDTO reviewDTO, Long prodId) {
//
//        // 멀티파트파일->S3에 업로드 하고 imageUrls 리스트로 받아옴
//        List<String> imagepath = awsS3Service.uploadS3(file);
//
//        // imageUrls를 받아서 DB에 업로드(tbl_review, tbl_review_img 동시에)..
//        // 추후 변경 1L -> prodId 로
//        reviewService.uploadDB(imagepath, reviewDTO, 2L);
//
//        return "redirect:/board/review-list";
//
//    }
    // 상품 관리 리스트(삭제 X 전체 테이블 출력용 코드)
//    @GetMapping("/management")
//    public String productManagement(Model model) {
//        List<ProductResponse> productList = productService.findProductAll();
//
//        model.addAttribute("productList",productList);
//        return "product/product-management";
//    }

    // 상품 리스트
//    @GetMapping("/list/all")
//    public String productList(Model model) {
//        List<ProductResponse> productList = productService.findProductBySalesDist("YES");
//
//        model.addAttribute("productList", productList);
//        return "product/product-list";
//    }

    // [상품 등록] - 화면으로 렌더링
    @GetMapping("/register")
    public String productRegister(Model model) {
        model.addAttribute("product", Product.builder().build());
        model.addAttribute("productOption", ProductOption.builder().build());
        return "product/product-register";
    }

    // [상품 등록] - POST 요청을 처리하여 상품을 등록하는 메서드
//    @PostMapping("/register")
//    public String processCreationForm(@Valid Product product, BindingResult result) {
//        if (result.hasErrors()) {
//            return "product/product-register";
//        } else {
//            // productService를 사용하여 상품을 등록
//            productService.save(product);
//
//            // html 파일이 아닌 /product/management 를 redirect해준다.
//            return "redirect:/product/management";
//        }
//    }

    // [상품 등록] - POST 요청을 처리하여 상품을 등록하는 메서드
    @PostMapping("/register")
    public String processCreationForm(@Valid Product product, @Valid ProductOption productOption, BindingResult result) {
        if (result.hasErrors()) {
            return "product/product-register";
        } else {
            // productService를 사용하여 상품을 등록
            productService.saveProduct(product);

            // productOptionService를 사용하여 상품 옵션을 등록
//            List<ProductOption> productOptions = productOptionService.getProductOptions();
            productOptionService.saveProductOption(productOption);
            System.out.println("222222222222");

            return "/product/product-register-detail";
        }
    }


    // GET 리뷰 작성 페이지 조회(해당 페이지의 상품 id 가져와서)
    @GetMapping("/register/{prodId}")
    public String productImage(@PathVariable Long prodId , Model model) {

        Product product = productService.findById(prodId);
//        String prodName = product.getProdName();

        String userId = "kiko139";
        System.out.println("3333333333333");

        model.addAttribute("userId", userId);
        model.addAttribute("product", product);

        System.out.println("4444444444444");
        return "/product/product-list";
    }

    // POST 리뷰 작성 (INSERT)
    @PostMapping(value = "/register/{prodId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileForProduct(@RequestParam(value = "image1", required = false) List<MultipartFile> files, ProductDTO productDTO
                            ,@RequestParam("prodId") Long prodId) {

        System.out.println("5555555555555");
        List<String> imagepath = null;

        // 멀티파트파일->S3에 업로드 하고 imageUrls 리스트로 받아옴
        if(!ObjectUtils.isEmpty(files) && !files.get(0).getOriginalFilename().equals("")){
            imagepath = awsS3ServiceProduct.uploadS3ForProduct(files);
        }

        // imageUrls를 받아서 DB에 업로드(tbl_review, tbl_review_img 동시에)..
        // 추후 변경 1L -> prodId 로
        productService.uploadDBForProduct(imagepath, productDTO, prodId);

        return "/product/product-register";
    }
}

