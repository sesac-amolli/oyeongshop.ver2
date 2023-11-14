package com.amolli.oyeongshop.ver2.board.controller;


import com.amolli.oyeongshop.ver2.board.dto.ReviewDTO;
import com.amolli.oyeongshop.ver2.board.dto.ReviewResponseDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.service.ReviewService;
import com.amolli.oyeongshop.ver2.s3.AwsS3Service;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
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
    public String findAllReview(Long prodId, Model model) {

        List<Review> reviews = reviewService.findByProdId(1L);

        List<ReviewResponseDTO> reviewdto = reviews.stream().map(ReviewResponseDTO::from).collect(Collectors.toList());

        model.addAttribute("reviewdto", reviewdto);

        return "board/review-list";
    }

    // GET 리뷰 작성 페이지 조회(해당 페이지의 상품 id 가져와서)
    @GetMapping("/review-write")
    public String reviewWrite() {
        return "/board/review-write";
    }

    // POST 리뷰 작성 (INSERT)

    @PostMapping(value = "/review-write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value = "image1", required = false) List<MultipartFile> files, ReviewDTO reviewDTO, Long prodId) {

        List<String> imagepath = null;

        // 멀티파트파일->S3에 업로드 하고 imageUrls 리스트로 받아옴
        if(!ObjectUtils.isEmpty(files) && !files.get(0).getOriginalFilename().equals("")){
            imagepath = awsS3Service.uploadS3(files);
        }

        // imageUrls를 받아서 DB에 업로드(tbl_review, tbl_review_img 동시에)..
        // 추후 변경 1L -> prodId 로
        reviewService.uploadDB(imagepath, reviewDTO, 2L);

        return "redirect:/board/review-list";
    }

    @GetMapping("/review-delete")
    public void deleteReview(@AuthenticationPrincipal PrincipalDetails details) {
        String userId = details.getUser().getUserId();
        System.out.println("UserId::" + userId);
    }
}
