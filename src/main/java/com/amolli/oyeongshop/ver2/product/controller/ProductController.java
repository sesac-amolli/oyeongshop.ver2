package com.amolli.oyeongshop.ver2.product.controller;

import com.amolli.oyeongshop.ver2.product.dto.ProductResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 상품 리스트
    @GetMapping("/list")
    public String productList(Model model) {
        List<ProductResponse> productList = productService.findProductAll();

        model.addAttribute("productList",productList);
        return "product/product-list";
    }

    @GetMapping("/list/{prodCategory}")
    public String getProductsByCategory(@PathVariable String prodCategory, Model model) {
        List<ProductResponse> productList = productService.getProductsByCategory(prodCategory);

        // 모델에 데이터 추가
        model.addAttribute("productList", productList);
        model.addAttribute("category", prodCategory);

        // 뷰 이름 반환 (타임리프 템플릿 이름)
        return "product/product-list";
    }

    // 상품 상세정보
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
        return mav;
    }

    // 상품 등록 화면으로 렌더링
    @GetMapping("/register")
    public String productRegister(Model model) {
        model.addAttribute("product", Product.builder().build());
        return "product/product-register";
    }

    // 상품 등록 POST 요청을 처리하는 메서드
    @PostMapping("/register")
    public String processCreationForm(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product/product-register";
        } else {
            // productService를 사용하여 상품을 등록
            productService.save(product);

            // html 파일이 아닌 /product/management 를 redirect해준다.
            return "redirect:/product/management";
        }
    }

    // 상품 관리 리스트(삭제 X 전체 테이블 출력용 코드)
//    @GetMapping("/management")
//    public String productManagement(Model model) {
//        List<ProductResponse> productList = productService.findProductAll();
//
//        model.addAttribute("productList",productList);
//        return "product/product-management";
//    }

    // 상품 관리 리스트 with Paging Navigation
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
//    @GetMapping("/detail/edit")
//    public String productDetailEdit( ) {
//        return "/product/product-detail-edit";
//    }


    @GetMapping("/edit/{prodId}")
    public String initUpdateOwnerForm(@PathVariable Long prodId, Model model) {

        model.addAttribute(productService.findById(prodId));

        return "/product/product-register";
    }
    @PostMapping("/edit/{prodId}")
    public String initUpdateForm(@Validated Product product, @PathVariable Long prodId) {
        product.setProdId(prodId);
        Product savedProduct = productService.save(product);
        return "redirect:/product/management";
    }
}

