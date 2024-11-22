package com.project.cdv_cinema.repository;

import com.project.cdv_cinema.entity.FoodComboDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodComboDetailRepository extends JpaRepository<FoodComboDetail, Long> {
}
