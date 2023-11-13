package com.amolli.oyeongshop.ver2.security.config.oauth.provider;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();

    String getPhone();
}
