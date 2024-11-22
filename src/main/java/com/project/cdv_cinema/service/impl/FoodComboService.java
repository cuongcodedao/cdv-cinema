package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.entity.FoodCombo;
import com.project.cdv_cinema.repository.FoodComboRepository;
import com.project.cdv_cinema.service.IFoodComboService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodComboService implements IFoodComboService {
    private final FoodComboRepository foodComboRepository;

    @Override
    public List<FoodCombo> findAllById(List<Long> ids) {
        return foodComboRepository.findAllById(ids);
    }

    @Override
    public FoodCombo findById(Long id) {
        return foodComboRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Can not find FoodCombo with id: " + id));
    }
}
