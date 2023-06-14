package com.learning.movie.service;

import com.learning.movie.constants.Constants;
import com.learning.movie.model.Auditorium;
import com.learning.movie.repo.AuditoriumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuditoriumService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    public Auditorium addAuditorium(Auditorium auditorium) {
        return auditoriumRepository.save(auditorium);
    }

    public Auditorium deleteAuditorium(Long auditoriumId) {
        Auditorium auditorium = auditoriumRepository.getById(auditoriumId);
        if (Objects.nonNull(auditorium)) {
            auditorium.setStatus(Constants.INACTIVE);
            return auditoriumRepository.save(auditorium);
        }
        return null;
    }
}
