package com.project.cdv_cinema.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(
            name="movie_genre",
            joinColumns =  @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name="movie_id")
    )
    private List<Movie> movies;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Constructor, getter, setter
}
