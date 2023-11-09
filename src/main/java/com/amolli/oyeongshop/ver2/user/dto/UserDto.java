package com.amolli.oyeongshop.ver2.user.dto;

import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.model.UserAddr;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
public class UserDto {
    private String userId;

    private String userName;

    private String userPwd;

    private String userPhone;

    private String userEmail;

    private String postcode;
    private String address;
    private String userAddrDetail;

    public User toEntity() {
        UserAddr userAddr = UserAddr.builder()
                .userAddrPostcode(postcode)
                .userAddr1(address)
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
