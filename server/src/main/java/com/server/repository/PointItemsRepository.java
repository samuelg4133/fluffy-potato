package com.server.repository;

import com.server.model.PointItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointItemsRepository extends JpaRepository<PointItems, Long> {
}
