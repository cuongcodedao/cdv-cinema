package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.entity.User;
import com.project.cdv_cinema.repository.UserRepository;
import com.project.cdv_cinema.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
