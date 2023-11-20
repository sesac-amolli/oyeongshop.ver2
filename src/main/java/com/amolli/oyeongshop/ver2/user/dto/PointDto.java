package com.amolli.oyeongshop.ver2.user.dto;

import com.amolli.oyeongshop.ver2.user.model.Point;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor
public class PointDto {
    private String pointDist;
    private String pointHistory;
    private Long pointAmount;
    private LocalDate pointDate;

    public PointDto(String pointDist, String pointHistory, Long pointAmount, LocalDate pointDate) {
        this.pointDist = pointDist;
        this.pointHistory = pointHistory;
        this.pointAmount = pointAmount;
        this.pointDate = pointDate;
    }

    public static PointDto from(Point point) {
        PointDto pointDto = new PointDto(
                point.getPointDist(),
                point.getPointHistory(),
                point.getPointAmount(),
                point.getPointDate()
        );
        return pointDto;
    }
}
