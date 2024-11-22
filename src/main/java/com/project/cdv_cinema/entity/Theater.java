package com.project.cdv_cinema.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    private String status;

    @OneToMany(mappedBy = "theater")
    private List<Room> rooms;

    @OneToMany(mappedBy = "theater")
    private List<Showtime> showtimes;

}
