package com.amolli.oyeongshop.ver2.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Entity
@Table(name = "tblQuestion")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    //@Column(name="") 안썼당
    //선언한 변수명과 실제 DB 컬럼의 이름과 동일하다면 굳이 선언하지 않아도 자동 매핑이 된다.
    @Id
    private String questionId;

    private String questionTitle;

    private String questionContent;

    private String questionUserId;

    private String questionCategory;

    private String questionPwd;

    private String questionProdId;

    private String questionOrderId;

    private String questionWriteDate;

    private String questionOpenFlag;

}
