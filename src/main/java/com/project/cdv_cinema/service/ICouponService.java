package com.project.cdv_cinema.service;

import com.project.cdv_cinema.dto.CouponDTO;
import com.project.cdv_cinema.entity.Coupon;

import java.util.List;

public interface ICouponService {
    List<CouponDTO> findByIds(List<Long> ids);
    List<CouponDTO> findAll();
}
