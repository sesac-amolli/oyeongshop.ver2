package com.amolli.oyeongshop.ver2.security.config.oauth.provider;

import java.util.Map;

public class KaKaoUserInfo implements OAuth2UserInfo{

    private  Map<String, Object> attributes;
    private  Map<String, Object> properties;

    public KaKaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        properties = (Map<String, Object>) attributes.get("properties");
    }

    @Override
    public String getProviderId() {
        return Long.toString((Long) attributes.get("id")) ;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getName() {
        return (String)properties.get("nickname");
    }

    @Override
    public String getPhone() {
        return null;
    }
}
