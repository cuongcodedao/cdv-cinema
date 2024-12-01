package com.project.cdv_cinema.service;

import com.project.cdv_cinema.dto.UserLoginDTO;
import com.project.cdv_cinema.entity.User;
import com.project.cdv_cinema.response.TokenResponse;
import com.project.cdv_cinema.response.UserResponse;
import com.project.cdv_cinema.service.impl.UserService;

public interface IUserService {
    User findById(Long id);
    TokenResponse login(UserLoginDTO userLoginDTO);
}
