package com.amolli.oyeongshop.ver2;

import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.dto.UserDTO;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;

    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetails userDetails){
        if (userDetails!=null){
            System.out.println(userDetails.getUser().getUserName());
            model.addAttribute("userName",userDetails.getUser().getUserName()+"님 환영합니다.");
        }
        return "/index";
    }


    @GetMapping("/sign-up")
    public String signUpForm() {
        return "/user/signup";
    }

    @PostMapping("/sign-up")
    public String signUp(UserDTO userDto){
        System.out.println("controller user :: "+userDto);
        userService.signUp(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/login";
    }

}
