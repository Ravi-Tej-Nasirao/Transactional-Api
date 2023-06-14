package com.learning.movie.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "seat_info")
public class SeatInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "show_id")
    private Long showId;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "seat_row")
    private String seatRow;

    @Column(name = "seat_number")
    private Long seatNumber;

    @Column(name = "price")
    private Float price;

    @Column(name = "seat_type")
    private Long seatType;

    @Column(name = "auditorium_id")
    private Long auditoriumId;

    @Column(name = "availability")
    private Integer availability;


}
