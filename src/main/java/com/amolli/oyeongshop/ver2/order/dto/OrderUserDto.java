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
        for(UserAddr userAddr : userAddrs){
            this.userAttnPostcode = userAddr.getUserAddrPostcode();
            this.userAttnAddr1 = userAddr.getUserAddr1();
            this.userAttnAddr2 = userAddr.getUserAddr2();
            this.userAttnDetail = userAddr.getUserAddrDetail();
        }
    }

    public OrderUserDto(User user) {
        this.userId = user.getUserId();
        this.userPoint = user.getUserPoint();
        this.userAttnName = user.getUserName();
        this.userAttnPhone = user.getUserPhone();
        this.userAttnEmail = user.getUserEmail();
        addOrderUserAddress(user.getUserAddrs());
    }
}
