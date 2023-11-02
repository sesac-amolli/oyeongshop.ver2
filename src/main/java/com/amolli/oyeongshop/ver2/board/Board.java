package com.amolli.oyeongshop.ver2.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Entity
@Table(name = "tblQuestion")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

}
