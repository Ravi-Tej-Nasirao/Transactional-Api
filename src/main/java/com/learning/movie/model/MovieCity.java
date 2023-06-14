package com.learning.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "movie_city")
@NoArgsConstructor
@AllArgsConstructor
public class MovieCity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "city_id", nullable = false)
    private Long cityId;

    @Column(name = "status")
    private Integer status;

    public MovieCity(Long movieId, Long cityId) {
        this.movieId = movieId;
        this.cityId = cityId;
    }
}
