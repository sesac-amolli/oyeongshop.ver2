package com.amolli.oyeongshop.ver2.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/list")
    public String index( ) {
        return "/product/product-list";
    }

    @GetMapping("/register")
    public String index2( ) {
        return "/product/product-register";
    }
    @GetMapping("/management")
    public String index3( ) {
        return "/product/product-management";
    }
    @GetMapping("/detail")
    public String index4( ) {
        return "/product/product-detail";
    }
    @GetMapping("/detail/edit")
    public String index5( ) {
        return "/product/product-detail-edit";
    }
}
