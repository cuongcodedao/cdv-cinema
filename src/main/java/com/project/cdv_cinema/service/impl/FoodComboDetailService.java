package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.dto.FoodComboDetailDTO;
import com.project.cdv_cinema.entity.Booking;
import com.project.cdv_cinema.entity.FoodCombo;
import com.project.cdv_cinema.entity.FoodComboDetail;
import com.project.cdv_cinema.repository.FoodComboDetailRepository;
import com.project.cdv_cinema.service.IBookingService;
import com.project.cdv_cinema.service.IFoodComboDetailService;
import com.project.cdv_cinema.service.IFoodComboService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodComboDetailService implements IFoodComboDetailService {
    private final FoodComboDetailRepository foodComboDetailRepository;
    private final IFoodComboService foodComboService;
    @Override
    public FoodComboDetail createFoodComboDetail(FoodComboDetailDTO foodComboDetailDTO) {
        FoodCombo foodCombo = foodComboService.findById(foodComboDetailDTO.getFoodComboId());
        FoodComboDetail foodComboDetail = new FoodComboDetail();
        foodComboDetail.setFoodCombo(foodCombo);
        foodComboDetail.setQuantity(foodComboDetailDTO.getQuantity());
        foodComboDetail.setUnitPrice(foodComboDetailDTO.getUnitPrice());

        return foodComboDetailRepository.save(foodComboDetail);

    }
}
