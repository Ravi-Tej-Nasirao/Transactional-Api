package com.learning.movie.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "payment_info")
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @Column(name = "booking_id", nullable = false)
    private Long bookingId;

    @Column(name = "promo_code")
    private Long promoCode;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "mode")
    private String mode;

    @Column(name = "status")
    private Integer status;

}
