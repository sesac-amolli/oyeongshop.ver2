package com.amolli.oyeongshop.ver2.user.repository;

import com.amolli.oyeongshop.ver2.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);

    User findByUserEmail(String userEmail);

}
