package com.server.repository;

import com.server.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    Point getById(Long id);

    @Query(value = "SELECT DISTINCT p from Point p " +
            "JOIN PointItems pi " +
            "ON p.id=pi.point.id " +
            "WHERE p.city = ?1 or p.uf = ?2 or pi.item.id= ?3")
    List<Point> getDistinctByCityOrUfOrOrItems(String city, String uf, Long id);
}
