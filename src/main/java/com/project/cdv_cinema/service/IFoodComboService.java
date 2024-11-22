package com.project.cdv_cinema.service;

import com.project.cdv_cinema.entity.FoodCombo;

import java.util.List;

public interface IFoodComboService {
    List<FoodCombo> findAllById(List<Long> ids);
    FoodCombo findById(Long id);
}
