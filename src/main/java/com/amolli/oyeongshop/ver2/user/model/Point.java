package com.amolli.oyeongshop.ver2.user.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tblPoint")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point extends BaseEntity{

    private String pointDist;

    private Long pointAmount;

    @CreationTimestamp
    private LocalDate pointDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
