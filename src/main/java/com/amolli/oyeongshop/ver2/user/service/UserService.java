package com.amolli.oyeongshop.ver2.user.service;

import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.user.dto.UserDto;
import com.amolli.oyeongshop.ver2.user.model.User;

public interface UserService {
    public void signUp(UserDto userDto);

    public User getUserById(String userId);
}
