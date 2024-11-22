package com.project.cdv_cinema.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="showtime_id")
    private Showtime showtime;

    @OneToMany(mappedBy = "booking")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "booking")
    @JsonManagedReference
    private List<FoodComboDetail> foodComboDetails;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToMany(mappedBy = "bookings")
    private List<Coupon> coupons;

    private LocalDateTime date;

    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;

    private String qrCode;
}
