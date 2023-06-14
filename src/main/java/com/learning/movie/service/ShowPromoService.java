package com.learning.movie.service;

import com.learning.movie.constants.Constants;
import com.learning.movie.model.ShowPromo;
import com.learning.movie.repo.ShowPromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.PathParam;
import java.util.List;

@Service
public class ShowPromoService {

    @Autowired
    private ShowPromoRepository showPromoRepository;

    public List<ShowPromo> getPromoByShowId(@PathParam("showId") Long showId) {
        return showPromoRepository.findByShowIdAndStatus(showId, Constants.ACTIVE);
    }


}
