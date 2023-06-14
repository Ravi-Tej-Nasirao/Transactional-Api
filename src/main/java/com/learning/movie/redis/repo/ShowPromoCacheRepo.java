package com.learning.movie.redis.repo;/*
 * @author : Ravi Tej Nasirao
 */

import com.learning.movie.model.ShowPromo;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class ShowPromoCacheRepo {

    private final String hashReference = "showPromo";

    @Resource(name = "redisTemplate")
    private HashOperations<String, Long, List<ShowPromo>> hashOperations;


    public void save(Long showId, List<ShowPromo> promos) {
        //creates one record in Redis DB if record with that Id is not present
        hashOperations.put(hashReference, showId, promos);
    }

    public void saveAllPromos(Map<Long, List<ShowPromo>> promos) {
        hashOperations.putAll(hashReference, promos);
    }

    public List<ShowPromo> findByShowId(Long showId) {
        return hashOperations.get(hashReference, showId);
    }

    public void deleteByshowId(Long showId) {
        hashOperations.delete(hashReference, showId);
    }

}
