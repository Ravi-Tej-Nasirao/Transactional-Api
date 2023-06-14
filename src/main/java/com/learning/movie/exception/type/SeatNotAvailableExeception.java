package com.learning.movie.exception.type;

public class SeatNotAvailableExeception extends RuntimeException {

    public SeatNotAvailableExeception(String message) {
        super(message);
    }

}
