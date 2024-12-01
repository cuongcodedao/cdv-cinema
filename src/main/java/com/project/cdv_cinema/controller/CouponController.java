package com.project.cdv_cinema.controller;


import com.project.cdv_cinema.service.impl.CouponService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/coupons")
public class CouponController{
    private final CouponService couponService;

    @GetMapping("/")
    public ResponseEntity<?> getCoupons(){
        try{
            return ResponseEntity.ok(couponService.findAll());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
