package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.service.IFileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService implements IFileService {
    @Override
    public Resource getFile(String fileName) throws MalformedURLException {
        Path path = Paths.get(fileName);
        Resource resource = new UrlResource(path.toUri());
        return resource;
    }
}
