package com.amolli.oyeongshop.ver2.user.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user_addr")
@Getter
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserAddr extends BaseEntity{

    private String userAddrPostcode;

    @Column(nullable = false)
    @ColumnDefault("'집'")
    private String userAddrCategory;

    private String userAddr1;

    private String userAddr2;

    private String userAddrDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setAddrs(User user) {
        this.user=user;
    }
}
