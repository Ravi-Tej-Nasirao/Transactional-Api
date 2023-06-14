package com.learning.movie.service;

import com.learning.movie.model.Theatre;
import com.learning.movie.repo.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public Theatre addTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public Theatre deleteTheatre(Long theatreId) {
        Theatre theatreDb = theatreRepository.findByTheatreIdAndStatus(theatreId, true);
        if (Objects.nonNull(theatreDb)) {
            theatreDb.setStatus(false);
            return theatreRepository.save(theatreDb);
        }
        return null;
    }
}
