package com.learning.movie.service;

import com.learning.movie.constants.Constants;
import com.learning.movie.model.Seat;
import com.learning.movie.repo.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;


    public List<Seat> addSeatsForAuditorium(List<Seat> seats) {

        return seatRepository.saveAll(seats);

    }

    public List<Seat> getSeatsForAuditorium(Long auditoriumId) {

        List<Seat> seats = seatRepository.getSeatsForAuditorium(auditoriumId);
        if (Objects.nonNull(seats) && !seats.isEmpty()) {
            return seats;
        }
        return null;
    }

    public List<Seat> deleteSeatsForAuditorium(List<Seat> seats) {

        seats.forEach(seat -> {
            seat.setStatus(Constants.INACTIVE);
        });
        return seatRepository.saveAll(seats);
    }
}
