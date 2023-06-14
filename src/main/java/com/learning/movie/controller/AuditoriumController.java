package com.learning.movie.controller;

import com.learning.movie.model.Auditorium;
import com.learning.movie.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/v1/auditorium")
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    @PostMapping
    public ResponseEntity<Auditorium> addAuditorium(@RequestBody Auditorium auditorium) {
        Auditorium auditoriumDb = auditoriumService.addAuditorium(auditorium);
        return new ResponseEntity<>(auditoriumDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{auditoriumId}")
    public ResponseEntity<Auditorium> deleteAuditorium(@PathVariable("auditoriumId") Long auditoriumId) {
        Auditorium auditorium = auditoriumService.deleteAuditorium(auditoriumId);
        if (Objects.nonNull(auditorium)) {
            return new ResponseEntity<>(auditorium, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
