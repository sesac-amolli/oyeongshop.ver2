package com.amolli.oyeongshop.ver2.board.s3;

import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhotoRepository extends JpaRepository<ReviewImg, Long> {


}
