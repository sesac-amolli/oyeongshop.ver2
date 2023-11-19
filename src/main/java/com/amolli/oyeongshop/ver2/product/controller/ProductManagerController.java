package com.amolli.oyeongshop.ver2.product.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/management/product")
@RequiredArgsConstructor
public class ProductManagerController {
    private final AwsS3ServiceProduct awsS3ServiceProduct;
    private final ProductService productService;
    private final ProductOptionService productOptionService;

    // [상품 목록] - 상품 관리 리스트 for Paging Navigation
    @GetMapping("")
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


    // [상품 등록] - 화면으로 렌더링
    @GetMapping("/register")
    public String productRegister(Model model) {
        model.addAttribute("product", Product.builder().build());
        model.addAttribute("productOption", ProductOption.builder().build());
        return "product/product-register";
    }


    // [상품 및 상품 옵션 등록] - POST 요청을 처리하여 상품을 등록하는 메서드
    @PostMapping("/register")
    public String processCreationForm(@Valid Product product, @Valid ProductOption productOption, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "product/product-register";
        } else {
            // productService를 사용하여 상품을 등록
            productService.saveProduct(product);

            // productOptionService를 사용하여 상품 옵션을 등록
//            List<ProductOption> productOptions = productOptionService.getProductOptions();
            productOptionService.saveProductOption(productOption);
            System.out.println("222222222222");

            // prodId를 URL에 추가하여 리다이렉트
            System.out.println(redirectAttributes.addAttribute("prodId", product.getProdId()));
            return "product/product-register-detail";
        }
    }

    // [상품 옵션 등록] - 상품 옵션의 prodId가 null인 인덱스를 prodId로 업데이트
    @ResponseBody
    @PostMapping("/register/{prodId}")
    public void updateProdIdWhereNullWhenProdReg(@PathVariable Long prodId) {
        productOptionService.updateProdIdWhereNull(prodId);
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


}
