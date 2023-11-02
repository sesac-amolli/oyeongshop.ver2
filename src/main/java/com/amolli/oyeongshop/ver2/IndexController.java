package com.amolli.oyeongshop.ver2;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@NoArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/login")
    public String index( ) {
        return "/login";
    }
}
