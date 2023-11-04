package com.amolli.oyeongshop.ver2.user.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tblCart")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity{

    @JoinColumn(name = "prod_opt_id")
    private ProductOption productOption;

    @JoinColumn(name = "user_id")
    private User user;

    private Date cartDate;

    private Long cartAmount;
}
