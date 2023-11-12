package com.amolli.oyeongshop.ver2.security.config.oauth;

import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.security.config.oauth.provider.GoogleUserInfo;
import com.amolli.oyeongshop.ver2.security.config.oauth.provider.KaKaoUserInfo;
import com.amolli.oyeongshop.ver2.security.config.oauth.provider.NaverUserInfo;
import com.amolli.oyeongshop.ver2.security.config.oauth.provider.OAuth2UserInfo;
import com.amolli.oyeongshop.ver2.user.model.Point;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/* 1.코드 받기 2. 엑세스 토큰(권한) 3.사용자프로필 정보 가져오기 4.자동 회원가입 진행 or 추가 정보 입력 받아서 회원가입 진행
 * 구글로그인 버튼 클릭 > 구글로그인창 > 로그인 완료 > code리턴(OAuth-Client라이브러리) > AccessToken요청
 * AccessToken으로 userRequest정보를 받는다.
 * userRequest정보로 loadUser메서드를 통해 회원프로필을 받는다.
 */

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //소셜로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //회원 프로필 받기
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes:: "+oAuth2User.getAttributes());
        //정보 제공 회사 별로 구분
        String provider = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = null;

        switch (provider){
            case "google":
                oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
                break;
            case "naver" :
                oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
                break;
            case "kakao" :
                oAuth2UserInfo = new KaKaoUserInfo(oAuth2User.getAttributes());
                break;
        }

        String providedId = oAuth2UserInfo.getProviderId();
        String userId = provider+"_"+providedId;
        String email = oAuth2UserInfo.getEmail();
        String phone = oAuth2UserInfo.getPhone();
        String name = oAuth2UserInfo.getName();
        String pwd = "OyeongShop";

        Optional<User> userEntity = userRepository.findById(userId);

        if(userEntity.isEmpty()){
            userEntity = Optional.ofNullable(User.builder()
                            .userId(userId)
                            .userPwd(pwd)
                            .userEmail(email)
                            .userName(name)
                            .userPhone(phone)
                            .userSnsDist(provider)
                            .build());
            Point point = new Point("적립", "회원가입 축하 적립금", 1000L);
            userEntity.get().givePoint(1000L, point);
            userRepository.save(userEntity.get());
        }

        return new PrincipalDetails(userEntity.get(), oAuth2User.getAttributes());
    }
}
