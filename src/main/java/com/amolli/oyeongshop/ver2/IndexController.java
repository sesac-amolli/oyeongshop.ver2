package com.amolli.oyeongshop.ver2;

import com.amolli.oyeongshop.ver2.user.dto.UserDto;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import com.amolli.oyeongshop.ver2.user.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String signUp(UserDto userDto){
        System.out.println("controller user :: "+userDto);
        userService.signUp(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/login";
    }

}
