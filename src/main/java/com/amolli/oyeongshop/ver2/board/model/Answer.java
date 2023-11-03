package com.amolli.oyeongshop.ver2.board.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "tblAnswer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {

    @Id
    @GeneratedValue
    private Long answerId;

    // 문의게시판id를 외래키로 참조
    @OneToOne
    @JoinColumn(name="questionId")
    private Question question;

    private String answerContents;

    private LocalDate answerDate;

    private String answerUserId;
    
}
