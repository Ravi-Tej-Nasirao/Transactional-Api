package com.learning.movie.controller;


import com.learning.movie.model.SeatLayoutPattern;
import com.learning.movie.model.Shows;
import com.learning.movie.repo.SeatInfoRepository;
import com.learning.movie.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @Autowired
    private SeatInfoRepository seatInfoRepository;

    @GetMapping("/{showId}")
    public List<SeatLayoutPattern> getLayout(@PathVariable("showId") Long showId) {
        return showService.getLayout(showId);
    }

    @PostMapping
    public Shows addShow(@RequestBody Shows show) {
        return showService.addShow(show);
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<Shows> deleteShow(@PathVariable("showId") Long showId) {
        Shows show = showService.deleteShow(showId);
        if (Objects.nonNull(show)) {
            return new ResponseEntity<>(show, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Shows> updateShow(@RequestBody Shows show) {
        Shows showDb = showService.addShow(show);
        if (Objects.nonNull(showDb)) {
            return new ResponseEntity<>(showDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
