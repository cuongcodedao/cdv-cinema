package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.dto.UserLoginDTO;
import com.project.cdv_cinema.entity.User;
import com.project.cdv_cinema.entity.UserPrincipal;
import com.project.cdv_cinema.repository.UserRepository;
import com.project.cdv_cinema.response.TokenResponse;
import com.project.cdv_cinema.response.UserResponse;
import com.project.cdv_cinema.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public TokenResponse login(UserLoginDTO userLoginDTO) throws RuntimeException{
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
            );
            if(authentication.isAuthenticated()) {
                TokenResponse tokenResponse = new TokenResponse();
                tokenResponse.setToken(jwtService.generateToken(userLoginDTO.getEmail()));
                return tokenResponse;
            }
            throw new AuthenticationException("Authentication Exception") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }

    }


}
