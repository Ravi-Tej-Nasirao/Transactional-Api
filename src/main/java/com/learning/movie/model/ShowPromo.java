package com.learning.movie.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "show_promo")
public class ShowPromo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "show_id", nullable = false)
    private Long showId;

    @Column(name = "promo_code_description")
    private String promoCodeDescription;

    @Column(name = "promo_discount")
    private Integer promoDiscount;

    @Column(name = "mode_applicable")
    private String modeApplicable;

    @Column(name = "status")
    private Integer status;

    @Column(name = "promo_type", nullable = false)
    private Integer promoType;

    @Column(name = "criteria", nullable = false)
    private Integer criteria;

}
