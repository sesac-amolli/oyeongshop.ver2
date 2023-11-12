package com.amolli.oyeongshop.ver2.user.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user_addr")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserAddr extends BaseEntity{

    private String userAddrPostcode;

    private String userAddr1;

    private String userAddrDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setAddrs(User user) {
        this.user=user;
    }
}
