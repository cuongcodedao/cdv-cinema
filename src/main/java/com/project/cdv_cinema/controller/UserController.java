package com.project.cdv_cinema.controller;

import com.project.cdv_cinema.dto.UserLoginDTO;
import com.project.cdv_cinema.response.TokenResponse;
import com.project.cdv_cinema.response.UserResponse;
import com.project.cdv_cinema.service.IUserService;
import com.project.cdv_cinema.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginDTO) {
        try{
            TokenResponse token = userService.login(loginDTO);
            return ResponseEntity.ok(token);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
