package com.amolli.oyeongshop.ver2.user.repository;

import com.amolli.oyeongshop.ver2.user.model.Point;
import com.amolli.oyeongshop.ver2.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
