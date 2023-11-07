package com.amolli.oyeongshop.ver2.user.service;

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
    public void signUp(User user){
        user.givePoint(1000L);
        String rawPwd = user.getUserPwd();
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);
        user.encPwd(encPwd);
        userRepository.save(user);
    }
}
