package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.dto.CouponDTO;
import com.project.cdv_cinema.entity.Coupon;
import com.project.cdv_cinema.repository.CouponRepository;
import com.project.cdv_cinema.service.ICouponService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService implements ICouponService {
    private final CouponRepository couponRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<CouponDTO> findByIds(List<Long> ids) {
        List<CouponDTO> coupons = new ArrayList<>();
        for (Long id : ids) {
            Coupon coupon = couponRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            coupons.add(modelMapper.map(coupon, CouponDTO.class));
        }
        return coupons;
    }

    @Override
    public List<CouponDTO> findAll() {
        List<Coupon> coupons = couponRepository.findAll();
        List<CouponDTO> couponDTOs = coupons.stream().map(coupon -> modelMapper.map(coupon, CouponDTO.class)).toList();
        return couponDTOs;
    }
}
