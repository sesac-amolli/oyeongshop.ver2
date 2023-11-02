package com.amolli.oyeongshop.ver2.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/QnA")
    public String index( ) {
        return "/board/qna-board-write";
    }

}
