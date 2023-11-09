package com.amolli.oyeongshop.ver2.product.controller;

import com.amolli.oyeongshop.ver2.product.dto.ProductResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    // 상품 상세정보
    @GetMapping("/detail/{prodId}")
    public ModelAndView productDetail(@PathVariable Long prodId, Model model) {
        // 중복 옵션 제거
        Product product = productService.findById(prodId);
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

    // 상품 관리 리스트
    @GetMapping("/management")
    public String productManagement(Model model) {
        List<ProductResponse> productList = productService.findProductAll();

        model.addAttribute("productList",productList);
        return "product/product-management";
    }

//    @GetMapping("/management")
//    public List<ProductResponse> productManagement(Model model) {
//        List<ProductResponse> productList = productService.findProductAll();
//
//        return productList;
//    }

//    @PostMapping(value = {"/register"})
//    public String processCreationForm(@Validated Product product, BindingResult result, @PathVariable Long prodId) {
//        if (result.hasErrors()) {
//            return "product/product-register";
//        }
//        else {
//            // OwnerService 생성 후 등록 처리 로직 구현, 완성
//            Product savedProduct = productService.save(product, prodId);
//            return "redirect:/product/" + savedProduct.getProdId();
//        }
//    }

    @GetMapping("/detail/edit")
    public String productDetailEdit( ) {
        return "/product/product-detail-edit";
    }
}

