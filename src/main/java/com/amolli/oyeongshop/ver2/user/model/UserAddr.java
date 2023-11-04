package com.amolli.oyeongshop.ver2.user.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tblUserAddr")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAddr extends BaseEntity{

    private String userAddrId;

    private Long userAddrPostcode;

    private String userAddr1;

    private String userAddr2;

    private String userAddr3;

    private String userAddrDetail;

    @JoinColumn(name = "user_id")
    private User user;

}
