package com.learning.movie.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "movie_city_lang_view")
public class MovieCityLangView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "show_date")
    private Date showDate;

    @Column(name = "screen_id")
    private Long screenId;

    @Column(name = "lang_id")
    private Long langId;

    @Column(name = "theatre_name")
    private String theatreName;

    @Column(name = "address")
    private String theatreAddress;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

}
