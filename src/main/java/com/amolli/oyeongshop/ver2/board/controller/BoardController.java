package com.amolli.oyeongshop.ver2.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
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

    @GetMapping("/review-lists")
    public String reviewList() { return "/board/review-list"; }

    @GetMapping("/review-write")
    public String reviewWrite() { return "/board/review-write"; }

    // 리뷰 수정 페이지 구현 x
 //   @GetMapping("")
 //   public String reviewModify() { return "/board/review-modify"; }

}
