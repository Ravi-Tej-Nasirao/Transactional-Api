package com.learning.movie.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "seat_count", nullable = false)
    private Integer seatCount;

    @Column(name = "seat_info", nullable = false)
    private String seatInfo;

    @Column(name = "show_id", nullable = false)
    private Long showId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "mode")
    private String mode;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

}
