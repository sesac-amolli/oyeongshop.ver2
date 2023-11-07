package com.amolli.oyeongshop.ver2.board.dto;

import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddReviewDTO {

        private String userId;

        private String reviewContent;

        private Long reviewRate;

        private String productName;

}


