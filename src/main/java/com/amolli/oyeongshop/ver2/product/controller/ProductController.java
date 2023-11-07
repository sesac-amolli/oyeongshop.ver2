package com.amolli.oyeongshop.ver2.product.controller;

import com.amolli.oyeongshop.ver2.product.dto.ProductResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public String productList(Model model) {
        List<Product> results = productService.findAll();
        List<ProductResponse> productList = new ArrayList<>();
        for (Product product : results) { // 이게 무슨 for문이라고 했지? 보고 무슨 코드이겠다 짐작하는 것으로는 부족하다. 공부 필요.
            productList.add(ProductResponse.from(product));
            System.out.println(productList);
        }

        model.addAttribute("productList",productList);
        return "product/product-list";
    }
//    @GetMapping("/list")
//    public String productList( ) {
//        return "/product/product-list";
//    }
    @GetMapping("/register")
    public String productRegister( ) {
        return "/product/product-register";
    }

//    @GetMapping("/management")
//    public List<Product> productMagnagement( ) {
//        List<Product> results = productService.findAll();
//
//        return results;
//    }

    @GetMapping("/management")
    public String productMagnagement( ) {
        List<Product> results = productService.findAll();

        return "/product/product-management";
    }

    @GetMapping("/detail")
    public String productDetail( ) {
        return "/product/product-detail";
    }

    @GetMapping("/detail/edit")
    public String productDetailEdit( ) {
        return "/product/product-detail-edit";
    }
}
