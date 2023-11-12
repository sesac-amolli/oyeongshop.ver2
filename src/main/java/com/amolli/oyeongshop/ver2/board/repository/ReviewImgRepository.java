package com.amolli.oyeongshop.ver2.board.repository;

import com.amolli.oyeongshop.ver2.board.dto.ReviewImgResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long> {

//    @Query(value = "select * from ReviewImg a JOIN Review b ON a.reviewId = b.reviewId WHERE b.prodId=:id")
//    List<ReviewImgResponseDTO> findAllById(@Param("id") Long reviewId);
}
