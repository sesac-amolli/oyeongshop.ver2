package com.amolli.oyeongshop.ver2.board.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "tbl_answer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String answerContents;

    @CreationTimestamp
    private LocalDate answerDate;

    private String answerUserId;

    // 문의게시판id를 외래키로 참조
    @OneToOne
    @JoinColumn(name="question_id")
    private Question question;
}
