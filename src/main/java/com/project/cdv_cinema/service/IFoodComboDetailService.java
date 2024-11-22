package com.project.cdv_cinema.service;

import com.project.cdv_cinema.dto.FoodComboDetailDTO;
import com.project.cdv_cinema.entity.FoodComboDetail;

public interface IFoodComboDetailService {
    FoodComboDetail createFoodComboDetail(FoodComboDetailDTO foodComboDetailDTO);
}
