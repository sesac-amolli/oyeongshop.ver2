package com.amolli.oyeongshop.ver2.user.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tbl_point")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class Point extends BaseEntity{

    private String pointDist;

    private String pointHistory;

    private Long pointAmount;

    @CreationTimestamp
    private LocalDate pointDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setPoint(User user) {
        this.user = user;
    }

    public Point(String pointDist, String pointHistory, Long pointAmount) {
        this.pointDist = pointDist;
        this.pointHistory = pointHistory;
        this.pointAmount = pointAmount;
    }
}