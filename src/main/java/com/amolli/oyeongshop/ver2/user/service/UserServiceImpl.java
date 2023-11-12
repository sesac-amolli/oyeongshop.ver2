package com.amolli.oyeongshop.ver2.user.service;

import com.amolli.oyeongshop.ver2.user.dto.UserDTO;
import com.amolli.oyeongshop.ver2.user.model.Point;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void signUp(UserDTO userDto){
        User user = userDto.toEntity();
        System.out.println(user);
        Point point = new Point("적립", "회원가입 축하 적립금", 1000L);
        user.givePoint(1000L, point);
        String rawPwd = user.getUserPwd();
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);
        user.encPwd(encPwd);
        userRepository.save(user);

    }
}
