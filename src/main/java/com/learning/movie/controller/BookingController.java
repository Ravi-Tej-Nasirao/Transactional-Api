package com.learning.movie.controller;


import com.learning.movie.model.BookingRequest;
import com.learning.movie.model.BookingResponse;
import com.learning.movie.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/book")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingResponse book(@RequestBody BookingRequest bookingRequest) {

        return bookingService.book(bookingRequest);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBookingDetails(@PathVariable("bookingId") Long bookingId) {

        BookingResponse bookingResponse = bookingService.getBookingDetails(bookingId);
        if (Objects.nonNull(bookingResponse)) {
            return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> deleteBooking(@PathVariable("bookingId") Long bookingId) {

        BookingResponse bookingResponse = bookingService.deleteBooking(bookingId);
        if (Objects.nonNull(bookingResponse)) {
            return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<BookingResponse> deleteBooking(@RequestBody List<Long> bookingIds) {

        BookingResponse bookingResponse = bookingService.deleteBookings(bookingIds);
        if (Objects.nonNull(bookingResponse)) {
            return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
