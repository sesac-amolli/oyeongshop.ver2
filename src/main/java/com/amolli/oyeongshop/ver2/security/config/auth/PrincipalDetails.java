package com.amolli.oyeongshop.ver2.security.config.auth;

import com.amolli.oyeongshop.ver2.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// 시큐리티가 '/login' 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다. > Security ContextHolder라는 키에 세션 정보를 저장시킨다.
// 시큐리티가 가진 세션에 들어갈 수 있는 오브젝트는 정해져있다. > Authentication 타입의 객체
// Authentication 안에는 User 정보가 있어야되는데, 이 User 정보는 UserDatils타입의 객체여야 한다.
public class PrincipalDetails implements UserDetails, OAuth2User {
    private User user;
    private Map<String, Object> attributes;

    //일반로그인 시 사용하는 생성자
    public PrincipalDetails(User user) {
        this.user = user;
    }

    //소셜로그인 시 사용하는 생성자
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes=attributes;
    }

    /* OAuth2User override */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }

    /* UserDetails override */
    // 해당 유저의 권한을 리턴하는 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getUserGrade();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getUserPwd();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 기간 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 기간 활성화 여부
    // 사이트 1년 동안 로그인 안하면, 휴면 계정 전환할 기능 같은거 구현할 때 사용
    @Override
    public boolean isEnabled() {
        return true;
    }


}
