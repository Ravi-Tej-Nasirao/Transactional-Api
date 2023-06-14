package com.learning.movie.controller;

import com.learning.movie.model.Theatre;
import com.learning.movie.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/v1/theatre")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping
    public Theatre addTheatre(@RequestBody(required = true) Theatre theatre) {
        return theatreService.addTheatre(theatre);
    }

    @DeleteMapping("/{theatreId}")
    public ResponseEntity<Theatre> deleteMovie(@PathVariable("theatreId") Long theatreId) {
        Theatre theatre = theatreService.deleteTheatre(theatreId);
        if (Objects.nonNull(theatre)) {
            return new ResponseEntity<>(theatre, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
