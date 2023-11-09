package com.amolli.oyeongshop.ver2.board.model;

import com.amolli.oyeongshop.ver2.board.dto.ReviewImgDTO;
import lombok.*;

import javax.persistence.*;

@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "tbl_review_img")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class ReviewImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewImageId;

    // s3 url
    private String reviewServerFileName;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

}
