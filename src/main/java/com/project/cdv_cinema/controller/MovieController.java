package com.project.cdv_cinema.controller;


import com.project.cdv_cinema.service.IMovieService;
import com.project.cdv_cinema.service.impl.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private final IMovieService movieService;

    @GetMapping("/")
    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok(movieService.findAllWithPoster());
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(movieService.findById(id));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
