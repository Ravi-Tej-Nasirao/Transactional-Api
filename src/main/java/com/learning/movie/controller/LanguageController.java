package com.learning.movie.controller;


import com.learning.movie.constants.Constants;
import com.learning.movie.model.Lang;
import com.learning.movie.repo.LangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/lang")
public class LanguageController {

    @Autowired
    public LangRepository langRepository;

    @GetMapping
    public List<Lang> findAllLanguages() {
        return langRepository.findAll();
    }

    @PostMapping
    public Lang addLanguage(@RequestBody Lang lang) {
        return langRepository.save(lang);
    }

    @DeleteMapping("/{langId}")
    public ResponseEntity<Lang> deleteLang(@PathVariable Integer langId) {
        Lang langDb = langRepository.getById(langId);
        if (Objects.nonNull(langDb)) {
            langDb.setStatus(Constants.INACTIVE);
            langDb = langRepository.save(langDb);
            return new ResponseEntity<>(langDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
