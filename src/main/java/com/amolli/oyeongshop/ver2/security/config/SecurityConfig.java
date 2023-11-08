package com.amolli.oyeongshop.ver2.Security.config;

import com.amolli.oyeongshop.ver2.Security.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터(=SecurityConfig)가 스프링 필터체인에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true) // secured 어노테이션 활성화
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    //패스워드 암호화하는 빈등록
    @Bean //해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                // user가 포함된 주소로 들어오면, 인증이 필요
                .antMatchers("/user/**").authenticated()
                // manager가 포함된 주소로 들어오면, 인증+권한이 필요 (권한은 ROLE_ADMIN 혹은 ROLE_MANAGER 이면 가능하다.)
                .antMatchers("/manager/**").access("hasRole('ADMIN') or hasRole('MANAGER')")
                // admin이 포함된 주소로 들어오면, 인증+권한이 필요 (권한은 ROLE_ADMIN만 가능하다.)
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                // 다른 요청은 모두 허용
                .anyRequest().permitAll()
                .and()
                .formLogin()
                // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                .loginPage("/login")
                .usernameParameter("userId")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/login")
                //구글 로그인이 완료된 뒤의 후 처리 > principalOauth2UserService 여기서 처리함
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
    }
}
