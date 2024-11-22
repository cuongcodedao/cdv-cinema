package com.project.cdv_cinema.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.jdi.PrimitiveValue;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String duration;

    private String director;

    private String actors;

    private int minAge;

    private String rating;

    private String trailer;

    private String producer;

    private String country;

    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Image> images ;

    private String poster;

    @ManyToMany(mappedBy = "movies")
    private List<Genre> genres;

    @OneToMany(mappedBy = "movie")
    private List<Showtime> showtimes;

}
