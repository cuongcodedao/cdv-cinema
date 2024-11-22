package com.project.cdv_cinema.configuration;

import com.google.gson.Gson;
import com.google.zxing.qrcode.QRCodeWriter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();
        return modelMapper;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
