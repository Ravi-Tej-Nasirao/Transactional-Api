package com.learning.movie.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BookingResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private Booking booking;
    private List<Booking> bookings;
}
