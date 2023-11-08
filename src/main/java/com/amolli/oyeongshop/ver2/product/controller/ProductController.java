package com.amolli.oyeongshop.ver2.product.controller;

import com.amolli.oyeongshop.ver2.product.dto.ProductResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public String productList(Model model) {
        List<ProductResponse> productList = productService.findProductAll();

        model.addAttribute("productList",productList);
        return "product/product-list";
    }

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
    @GetMapping("/register")
    public String productRegister( ) {
        return "product/product-register";
    }

    @GetMapping("/detail/edit")
    public String productDetailEdit( ) {
        return "/product/product-detail-edit";
    }
}
