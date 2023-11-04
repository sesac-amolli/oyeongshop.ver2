package com.amolli.oyeongshop.ver2.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/list")
    public String productList( ) {
        return "/product/product-list";
    }

    @GetMapping("/register")
    public String productRegister( ) {
        return "/product/product-register";
    }
    @GetMapping("/management")
    public String productMagnagement( ) {
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
