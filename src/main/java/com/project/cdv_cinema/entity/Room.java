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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String status;

    private int maxNumberOfSeat;

    private int numberOfRows;

    private int numberOfColumns;

    @OneToMany(mappedBy = "room")
    private List<Showtime> showtimes;

    @OneToMany(mappedBy = "room")
    private List<Seat> seats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="theater_id")
    private Theater theater;

}
