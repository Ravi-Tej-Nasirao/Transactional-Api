package com.learning.movie.service;


import com.learning.movie.enums.BookingEnum;
import com.learning.movie.model.PaymentInfo;
import com.learning.movie.repo.PaymentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PaymentService {

    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    public boolean processPayment(PaymentInfo paymentInfo) {

        paymentInfo = paymentInfoRepository.save(paymentInfo);

        String transactionId = payAmount(paymentInfo);
        if (Objects.nonNull(transactionId)) {
            paymentInfo.setTransactionId(transactionId);
        }
        Integer status = Objects.nonNull(transactionId) ? BookingEnum.SUCCESS.ordinal() : BookingEnum.FAIL.ordinal();
        paymentInfo.setStatus(status);
        paymentInfoRepository.save(paymentInfo);
        return status == 2 ? true : false;
    }

    public boolean refundPayment(Long bookingId) {

        PaymentInfo paymentInfo = paymentInfoRepository.findPaymentDetailsByBookingId(bookingId);
        if (Objects.nonNull(paymentInfo)) {
            boolean refundStatus = refundAmount(paymentInfo);
            paymentInfo.setStatus(refundStatus ? BookingEnum.SUCCESS.ordinal() : BookingEnum.FAIL.ordinal());
            paymentInfoRepository.save(paymentInfo);
            return refundStatus;
        }
        return false;
    }

    public boolean refundPayment(List<Long> bookingIds) {

        List<PaymentInfo> paymentsInfo = paymentInfoRepository.findPaymentDetailsByBookingIds(bookingIds);
        if (Objects.nonNull(paymentsInfo) && !paymentsInfo.isEmpty()) {
            boolean refundStatus = refundAmount(paymentsInfo);
            paymentsInfo.forEach(paymentInfo -> {
                paymentInfo.setStatus(refundStatus ? BookingEnum.SUCCESS.ordinal() : BookingEnum.FAIL.ordinal());
            });
            paymentInfoRepository.saveAll(paymentsInfo);
            return refundStatus;
        }
        return false;
    }

    private String payAmount(PaymentInfo paymentInfo) {

        // logic for payment gateway.

        return "UTR-123";

    }

    private Boolean refundAmount(PaymentInfo paymentInfo) {

        // logic for payment gateway.

        return true;

    }

    private Boolean refundAmount(List<PaymentInfo> paymentInfo) {

        // logic for payment gateway.

        return true;

    }
}
