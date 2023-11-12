package com.amolli.oyeongshop.ver2.user.controller;

import com.amolli.oyeongshop.ver2.user.dto.CartCreateRequestDTO;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import com.amolli.oyeongshop.ver2.user.response.Response;
import com.amolli.oyeongshop.ver2.user.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    // 장바구니 담기
    @PostMapping("/carts")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody CartCreateRequestDTO req){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(authentication.getName())
                .orElseThrow();
        cartService.create(req, user);
        return Response.success();
    }

}
