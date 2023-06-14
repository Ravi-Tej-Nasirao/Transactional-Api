package com.learning.movie.controller;

import com.learning.movie.model.Seat;
import com.learning.movie.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<List<Seat>> addSeatsForAuditorium(@RequestBody List<Seat> seats) {

        List<Seat> seatsDb = seatService.addSeatsForAuditorium(seats);
        if (seatsDb == null || !seatsDb.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(seatsDb, HttpStatus.CREATED);
    }

    @GetMapping("/{auditoriumId}")
    public ResponseEntity<List<Seat>> getSeatsForAuditorium(@PathVariable Long auditoriumId) {

        List<Seat> seats = seatService.getSeatsForAuditorium(auditoriumId);
        if (Objects.nonNull(seats)) {
            return new ResponseEntity<>(seats, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<List<Seat>> deleteSeats(@RequestBody List<Seat> seats) {

        List<Seat> seatsDb = seatService.deleteSeatsForAuditorium(seats);
        if (Objects.nonNull(seatsDb)) {
            return new ResponseEntity<>(seatsDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
