package com.amolli.oyeongshop.ver2.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartItemUpdateWrapper {
    private List<CartItemUpdateDTO> cartItemUpdates;

    // 세터 및 게터 메소드
}
