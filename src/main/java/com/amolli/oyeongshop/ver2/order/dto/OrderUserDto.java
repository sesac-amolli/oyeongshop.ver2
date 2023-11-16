package com.amolli.oyeongshop.ver2.order.dto;

import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.model.UserAddr;
import lombok.Data;

import java.util.List;

@Data
public class OrderUserDto {

    private String userId;

    private Long userPoint;

    private String userAttnName;

    private String userAttnPhone;

    private String userAttnEmail;

    private String userAttnPostcode;

    private String userAttnAddr1;

    private String userAttnAddr2;

    private String userAttnDetail;

    private void addOrderUserAddress(List<UserAddr> userAddrs){
        if (userAddrs != null && !userAddrs.isEmpty()) {
        for(UserAddr userAddr : userAddrs){
            this.userAttnPostcode = userAddr.getUserAddrPostcode();
            this.userAttnAddr1 = userAddr.getUserAddr1();
            this.userAttnAddr2 = userAddr.getUserAddr2();
            this.userAttnDetail = userAddr.getUserAddrDetail();
        }
        } else{
            // 주소 정보가 없는 경우 기본값 또는 처리를 수행하도록 설정 (여기서는 빈 값으로 초기화)
            this.userAttnPostcode = "";
            this.userAttnAddr1 = "";
            this.userAttnAddr2 = "";
            this.userAttnDetail = "";
        }
    }

    public OrderUserDto(User user) {
        this.userId = user.getUserId();
        this.userPoint = user.getUserPoint();
        this.userAttnName = user.getUserName();
        this.userAttnPhone = user.getUserPhone();
        this.userAttnEmail = user.getUserEmail();
        addOrderUserAddress(user.getUserAddrs().orElse(null));
    }
}
