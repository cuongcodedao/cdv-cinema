package com.project.cdv_cinema.service;


import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

public interface IFileService {
    Resource getFile(String fileName) throws MalformedURLException;
}
