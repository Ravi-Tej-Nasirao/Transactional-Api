package com.learning.movie.repo;

import com.learning.movie.model.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {

    @Modifying
    @Query("select pi from PaymentInfo pi where pi.bookingId = :bookingId")
    PaymentInfo findPaymentDetailsByBookingId(@Param("bookingId") Long bookingId);

    @Modifying
    @Query("select pi from PaymentInfo pi where pi.bookingId in :bookingIds")
    List<PaymentInfo> findPaymentDetailsByBookingIds(@Param("bookingIds") List<Long> bookingIds);
}
