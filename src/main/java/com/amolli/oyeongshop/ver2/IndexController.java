package com.amolli.oyeongshop.ver2;

import com.amolli.oyeongshop.ver2.product.service.ProductService;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.dto.UserDTO;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;
    private final ProductService productService;

    //메인 페이지
    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetails userDetails){
        if (userDetails!=null){
            model.addAttribute("userName",userDetails.getUser().getUserName()+"님 환영합니다.");
        }
        return "/index";
    }

    //회원가입 페이지
    @GetMapping("/sign-up")
    public String signUpForm() {
        return "/user/signup";
    }

    //회원가입 실행
    @PostMapping("/sign-up")
    public String signUp(UserDTO userDto){
        userService.signUp(userDto);
        return "redirect:/login";
    }

    //로그인 페이지
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false)String error,
                            @RequestParam(value = "exception", required = false)String exception,
                            Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/login";
    }

}
