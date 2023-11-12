package com.amolli.oyeongshop.ver2;

import com.amolli.oyeongshop.ver2.user.dto.UserDTO;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@NoArgsConstructor
@Controller
public class IndexController {

    @Autowired
    UserService userService;


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
