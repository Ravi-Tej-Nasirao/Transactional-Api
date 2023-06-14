package com.learning.movie.redis.repo;


import com.learning.movie.model.Lang;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LangCacheRepo {

    private final String hashReference = "Language";

    @Resource(name = "redisTemplate")
    private HashOperations<String, Integer, Lang> hashOperations;

    public void save(Integer langId, Lang language) {
        //creates one record in Redis DB if record with that Id is not present
        hashOperations.put(hashReference, langId, language);
    }

    public List<Lang> findAll() {
        Map<Integer, Lang> languagesMap = hashOperations.entries(hashReference);
        return languagesMap.entrySet().stream().map(entry->entry.getValue()).collect(Collectors.toList());
    }

}


