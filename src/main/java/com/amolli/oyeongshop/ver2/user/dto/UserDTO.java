package com.amolli.oyeongshop.ver2.user.dto;

import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.model.UserAddr;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
public class UserDTO {
    private String userId;

    private String userName;

    private String userPwd;

    private String userPhone;

    private String userEmail;

    private String postcode;
    private String address1;
    private String address2;
    private String userAddrDetail;

    public User toEntity() {
        UserAddr userAddr = UserAddr.builder()
                .userAddrPostcode(postcode)
                .userAddr1(address1)
                .userAddr2(address2)
                .userAddrDetail(userAddrDetail)
                .build();

        List<UserAddr> userAddrs = new ArrayList<>();
        userAddrs.add(userAddr);

        User user = User.builder()
                .userId(userId)
                .userName(userName)
                .userPwd(userPwd)
                .userPhone(userPhone)
                .userEmail(userEmail)
                .userAddrs(userAddrs)
                .build();

        userAddr.setAddrs(user);

        return user;
    }


}
