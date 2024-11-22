package com.project.cdv_cinema.repository;

import com.project.cdv_cinema.entity.FoodCombo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodComboRepository extends JpaRepository<FoodCombo, Long> {
}
