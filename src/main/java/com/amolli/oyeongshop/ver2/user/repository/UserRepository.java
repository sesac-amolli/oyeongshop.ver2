package com.amolli.oyeongshop.ver2.user.repository;

import com.amolli.oyeongshop.ver2.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);

    User findByUserEmail(String userEmail);

    // userAddr 테이블의 지연로딩으로 User 생성시, 주소정보도 함께 가져와 주문정보 셋팅을 위해 추가한 코드
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userAddrs a WHERE u.userId = :userId")
    Optional<User> findByIdWithUserAddrs(@Param("userId") String userId);


}
