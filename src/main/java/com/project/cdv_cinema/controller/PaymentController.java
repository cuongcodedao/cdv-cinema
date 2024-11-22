package com.project.cdv_cinema.controller;

import com.project.cdv_cinema.dto.PaymentDTO;
import com.project.cdv_cinema.response.BookingResponse;
import com.project.cdv_cinema.service.impl.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public String submidOrder(@RequestBody PaymentDTO paymentDTO,
                              HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = paymentService.createOrder(request, paymentDTO, baseUrl);
        return vnpayUrl;
    }


    @GetMapping("/vnpay-payment-return")
    public void paymentCompleted(HttpServletRequest request, HttpServletResponse response) {
        int paymentStatus = paymentService.orderReturn(request);
        try {
            response.sendRedirect("http://localhost:3000/booking-status?status="+paymentStatus + "&bookingId="+request.getParameter("vnp_TxnRef"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}