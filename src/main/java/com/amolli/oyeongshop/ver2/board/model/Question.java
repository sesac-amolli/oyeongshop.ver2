package com.amolli.oyeongshop.ver2.board.model;

import com.amolli.oyeongshop.ver2.product.model.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Entity
@Table(name = "tbl_question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {
    //@Column(name="") 안썼당
    //선언한 변수명과 실제 DB 컬럼의 이름과 동일하다면 굳이 선언하지 않아도 자동 매핑이 된다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String questionTitle;

    private String questionContent;

    private String questionUserId;

    private String questionCategory;

    private Long questionPwd;

    private LocalDate questionWriteDate;

    private String questionOpenFlag;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;

    // answer를 조회했을때 question도 같이 조회되는것이 좋겠다고 생각하면 onetoone을 이렇게
    // answer가 question의 외래키를 갖는것이 좋을것 같아서.. 그치만 onetoone은 정하기 나름이라고 함!
    @OneToOne(mappedBy = "question")
    private Answer answer;

}
