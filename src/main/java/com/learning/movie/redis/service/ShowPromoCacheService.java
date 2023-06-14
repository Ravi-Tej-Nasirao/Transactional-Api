package com.learning.movie.redis.service;


import com.learning.movie.constants.Constants;
import com.learning.movie.model.ShowPromo;
import com.learning.movie.redis.repo.ShowPromoCacheRepo;
import com.learning.movie.repo.ShowPromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShowPromoCacheService {

    @Autowired
    private ShowPromoCacheRepo showPromoCacheRepo;

    @Autowired
    private ShowPromoRepository showPromoRepository;

    public List<ShowPromo> getPromoByShowId(@PathParam("showId") Long showId){
        return showPromoRepository.findByShowIdAndStatus(showId, Constants.ACTIVE);
    }
    public void loadPromosByShowId(){

        List<ShowPromo> promos = showPromoRepository.getActivePromos(Constants.ACTIVE);

        Map<Long, List<ShowPromo>> showPromos =
                promos.stream()
                        .collect(Collectors.groupingBy(
                                ShowPromo::getShowId,
                                Collectors.mapping(
                                        Function.identity(), Collectors.toList()
                                )));
        showPromoCacheRepo.saveAllPromos(showPromos);
    }
}
