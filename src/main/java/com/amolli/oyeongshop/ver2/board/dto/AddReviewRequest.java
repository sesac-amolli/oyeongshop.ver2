package com.amolli.oyeongshop.ver2.board.dto;

import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddReviewRequest {

    private String userId;

    private String reviewContent;

    private Long reviewRate;

    private LocalDate reviewWriteDate;

    // 상품id가 외래키라서 선언
    private Product product;

    private List<ReviewImg> reviewImgs = new ArrayList<ReviewImg>();

}
