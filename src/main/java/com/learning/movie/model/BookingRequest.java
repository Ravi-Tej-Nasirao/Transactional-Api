package com.learning.movie.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookingRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long showId;
    private String userId;
    private String mode;
    private List<SeatInfo> seatsInfo = new ArrayList<>();
    private Long promoId;


}
