package com.amolli.oyeongshop.ver2.board.controller;


import com.amolli.oyeongshop.ver2.board.dto.ReviewDTO;
import com.amolli.oyeongshop.ver2.board.dto.ReviewResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.service.ReviewService;
import com.amolli.oyeongshop.ver2.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final AwsS3Service awsS3Service;
    private final ReviewService reviewService;

    @GetMapping("/lists")
    public String qnaList( ) {
        return "/board/qna-product-list";
    }

    @GetMapping("/write")
    public String qnaWrite() { return "/board/qna-product-write"; }

    @GetMapping("/pwd")
    public String pwdCheck() { return "/board/pwd-check"; }

    @GetMapping("/modify")
    public String qnaModify() { return "/board/qna-product-modify"; }


    // 리뷰 게시판
    // GET 리뷰 페이지 조회(상품ID에 따른 리뷰들)
    @GetMapping("/review-list")    //("/review-list/{prodId}") 로 변경
    public List<ReviewResponseDTO> findAllReview(Long prodId) {

        List<Review> allreviews = reviewService.findByProdId(1L);

        List<ReviewResponseDTO> reviewdto = allreviews.stream().map(ReviewResponseDTO::from).collect(Collectors.toList());
        System.out.println("!!allReviewDTO!!" + reviewdto);

        return reviewdto;
    }

    // GET 리뷰 리스트 조회(해당 페이지의 상품 id 가져와서)
    @GetMapping("/review-write")
    public String reviewWrite() { return "/board/review-write"; }

    // POST 리뷰 작성 (INSERT)
    @ResponseBody
    @PostMapping(value = "/review-write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("image1") List<MultipartFile> file, ReviewDTO reviewDTO, Long prodId) {

        // 멀티파트파일->S3에 업로드 하고 imageUrls 리스트로 받아옴
        List<String> imagepath = awsS3Service.uploadS3(file);

        // imageUrls를 받아서 DB에 업로드(tbl_review, tbl_review_img 동시에)..
        // 추후 변경 1L -> prodId 로
        reviewService.uploadDB(imagepath, reviewDTO, 1L);

        return "redirect:/board/review-list";

    }
}