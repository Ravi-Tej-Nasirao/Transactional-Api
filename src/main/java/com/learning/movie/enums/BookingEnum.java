package com.learning.movie.enums;

public enum BookingEnum {

    INPROGRESS(1),
    SUCCESS(2),
    FAIL(3),
    REFUND_COMPLETE(4),
    REFUND_FAIL(5);

    private final int status;

    BookingEnum(int status) {
        this.status = status;
    }
}
