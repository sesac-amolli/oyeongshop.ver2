package com.amolli.oyeongshop.ver2.board.repository;

import com.amolli.oyeongshop.ver2.board.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Question, Long> {
}
