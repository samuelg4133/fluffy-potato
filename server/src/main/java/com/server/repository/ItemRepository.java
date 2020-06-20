package com.server.repository;

import com.server.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item getById(Long id);

    @Query(value = "SELECT i FROM Item i JOIN PointItems pi ON i.id = pi.item.id WHERE pi.point.id = ?1")
    List<Item> findPointItems(Long id);
}
