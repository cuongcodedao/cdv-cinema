package com.project.cdv_cinema.dto;

import com.project.cdv_cinema.entity.MovieStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO {
    private Long id;

    private String name;

    private String description;

    private String duration;

    private String director;

    private String rating;

    private String actors;

    private int minAge;

    private String trailer;

    private String releaseDate;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    private List<ImageDTO> images;

    private String imagePoster;

    private List<GenreDTO> genres;

    private String producer;

    private String country;
}
