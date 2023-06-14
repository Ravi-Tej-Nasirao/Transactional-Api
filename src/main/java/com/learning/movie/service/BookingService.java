package com.learning.movie.service;


import com.learning.movie.enums.BookingEnum;
import com.learning.movie.exception.type.SeatNotAvailableExeception;
import com.learning.movie.model.*;
import com.learning.movie.repo.BookingRepository;
import com.learning.movie.repo.ShowPromoRepository;
import com.learning.movie.repo.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private ShowPromoRepository showPromoRepository;

    @Autowired
    private PaymentService paymentService;

    @Transactional
    public BookingResponse book(BookingRequest bookingRequest) throws SeatNotAvailableExeception {
        BookingResponse bookingResponse = new BookingResponse();

        try {

            Booking booking = new Booking();
            booking.setShowId(bookingRequest.getShowId());
            booking.setMode(bookingRequest.getMode());
            booking.setSeatCount(bookingRequest.getSeatsInfo().size());
            booking.setCreatedTime(LocalDateTime.now());
            booking.setStatus(BookingEnum.INPROGRESS.ordinal());
            booking.setUserId(bookingRequest.getUserId());
            String seatDetails = bookingRequest.getSeatsInfo()
                    .stream().map(info -> info.getSeatRow() + "-" + info.getSeatNumber()).collect(Collectors.joining(", "));
            booking.setSeatInfo(seatDetails);

            booking = bookingRepository.save(booking);

            List<ShowSeat> showSeats = new ArrayList<>();

            float totalPrice = 0.0f;

            for (SeatInfo seatInfo : bookingRequest.getSeatsInfo()) {
                ShowSeat showSeat = new ShowSeat();
                showSeat.setShowId(bookingRequest.getShowId());
                showSeat.setSeatId(seatInfo.getSeatId());
                showSeat.setBookingId(booking.getId());
                showSeat.setStatus(BookingEnum.INPROGRESS.ordinal());
                showSeat.setPrice(seatInfo.getPrice());
                totalPrice = totalPrice + seatInfo.getPrice();
                showSeats.add(showSeat);
            }

            showSeats = showSeatRepository.saveAll(showSeats);

            boolean paymentStatus = processPayment(bookingRequest, booking, totalPrice);
            booking.setStatus(paymentStatus ? BookingEnum.SUCCESS.ordinal() : BookingEnum.FAIL.ordinal()); //success
            booking = bookingRepository.save(booking);

            if (paymentStatus) {
                showSeats.forEach(seat -> {
                    seat.setStatus(BookingEnum.SUCCESS.ordinal());
                });
                showSeatRepository.saveAll(showSeats);
                bookingResponse.setMessage("Booking success.");
            } else {
                showSeatRepository.deleteAllInBatch(showSeats);
                bookingResponse.setMessage("Booking failed.");
            }
            bookingResponse.setBooking(booking);

        } catch (Exception e) {
            throw new SeatNotAvailableExeception("The seat has been already booked by another user, Kindly select other one.");
        }
        return bookingResponse;
    }

    private boolean processPayment(BookingRequest bookingRequest, Booking booking, float totalPrice) {

        ShowPromo promo = showPromoRepository.getById(bookingRequest.getPromoId());

        if (Objects.nonNull(promo) && Objects.equals(booking.getMode(), "1")
                && promo.getCriteria() == 0 && promo.getPromoType() == 2) {
            totalPrice = totalPrice - promo.getPromoDiscount();
        } else if (Objects.nonNull(promo) && Objects.equals(booking.getMode(), "1")
                && promo.getCriteria() != 0 && promo.getPromoType() == 1) {
            int seatCount = bookingRequest.getSeatsInfo().size();
            if (seatCount > promo.getCriteria()) {
                float pricePerSeat = totalPrice / (float) seatCount;
                int i = 0;
                float amount = 0.0f;
                while (i < seatCount) {
                    if (i < promo.getCriteria()) {
                        amount = amount + pricePerSeat;
                        i++;
                        continue;
                    }
                    amount = amount + (pricePerSeat * ((100.0f - promo.getPromoDiscount()) / 100.0f));
                    i++;
                }
                totalPrice = amount;
            }
        } else if (Objects.nonNull(promo) && Objects.equals(booking.getMode(), "1")
                && promo.getCriteria() == 0 && promo.getPromoType() == 1) {
            totalPrice = totalPrice * (promo.getPromoDiscount() / 100.0f);
        }

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setBookingId(booking.getId());
        paymentInfo.setAmount(totalPrice);
        paymentInfo.setStatus(BookingEnum.INPROGRESS.ordinal());
        paymentInfo.setMode(booking.getMode());
        paymentInfo.setPromoCode(bookingRequest.getPromoId());

        return paymentService.processPayment(paymentInfo);

    }

    public BookingResponse getBookingDetails(Long bookingId) {

        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setBooking(booking.get());
            bookingResponse.setMessage("Booking details");
            return bookingResponse;
        }
        return null;
    }

    @Transactional
    public BookingResponse deleteBooking(Long bookingId) {

        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            Booking bookingDetails = booking.get();
            if (paymentService.refundPayment(bookingId)) {
                bookingDetails.setStatus(BookingEnum.REFUND_COMPLETE.ordinal());
                showSeatRepository.deleteAllByBookingIds(Collections.singletonList(bookingDetails.getId()));
                BookingResponse bookingResponse = new BookingResponse();
                bookingResponse.setBooking(bookingDetails);
                bookingResponse.setMessage("Booking cancellation details");
                return bookingResponse;
            } else {
                bookingDetails.setStatus(BookingEnum.REFUND_FAIL.ordinal());
            }
            bookingDetails = bookingRepository.save(bookingDetails);
        }
        return null;
    }

    @Transactional
    public BookingResponse deleteBookings(List<Long> bookingIds) {

        List<Booking> bookings = bookingRepository.findAllById(bookingIds);
        if (Objects.nonNull(bookings) && !bookings.isEmpty()) {
            List<Long> bookingDbIds = new ArrayList<>();
            bookings.forEach(booking -> {
                booking.setStatus(0);
                bookingDbIds.add(booking.getId());
            });
            bookings = bookingRepository.saveAll(bookings);
            showSeatRepository.deleteAllByBookingIds(bookingDbIds);
            if (paymentService.refundPayment(bookingDbIds)) {
                BookingResponse bookingResponse = new BookingResponse();
                bookingResponse.setBookings(bookings);
                bookingResponse.setMessage("Booking cancellation details");
                return bookingResponse;
            }
        }
        return null;
    }
}
