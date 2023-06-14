package com.learning.movie.service;

import com.learning.movie.constants.Constants;
import com.learning.movie.exception.type.ShowConflictException;
import com.learning.movie.model.SeatInfo;
import com.learning.movie.model.SeatLayoutPattern;
import com.learning.movie.model.Shows;
import com.learning.movie.repo.BookingRepository;
import com.learning.movie.repo.SeatInfoRepository;
import com.learning.movie.repo.ShowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private SeatInfoRepository seatInfoRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    public List<SeatLayoutPattern> getLayout(Long showId) {

        List<SeatInfo> seatsInfo = seatInfoRepository.findByAuditoriumId(showId);

        Map<String, List<SeatInfo>> map = new HashMap<>();

        seatsInfo.forEach(s ->
                map.computeIfAbsent(s.getSeatRow(), k -> new ArrayList<>()) // or LinkedHashSet
                        .add(s));

        List<SeatLayoutPattern> patterns = new ArrayList<>();

        // Sample output
        // {"A":["1-1-1-50.0","1-2-1-50.0","1-3-0-50.0","1-4-0-50.0","1-5-0-50.0","3-6-0-0.0","3-7-0-0.0"],
        //  "B":["2-1-0-20.0","2-2-0-20.0","2-3-0-20.0","2-4-0-20.0","3-5-0-0.0","3-6-0-0.0"]}
        map.forEach((k, v) -> {
            List<String> rowPattern = v.stream()
                    .map(str -> str.getSeatType() + "-" + str.getSeatNumber() + "-" + str.getAvailability() + "-" + str.getPrice())
                    .collect(Collectors.toList());
            SeatLayoutPattern seatLayoutPattern = new SeatLayoutPattern(k, rowPattern);
            patterns.add(seatLayoutPattern);
        });

        return patterns;
    }

    public Shows addShow(Shows show) {
        // see for conflicts of show times.
        List<Shows> shows = showsRepository.getConflictingTimeRecords(show.getStartTime(), show.getEndTime(), show.getAuditoriumId());
        if (shows == null || shows.isEmpty()) {
            return showsRepository.save(show);
        }
        throw new ShowConflictException("Overlapping show is already scheduled.");
    }

    @Transactional
    public Shows deleteShow(Long showId) {
        Optional<Shows> show = showsRepository.findById(showId);
        if (show.isPresent()) {
            Shows showDetails = show.get();
            List<Long> bookingIds = bookingRepository.findAllBookingByShowId(showDetails.getId());
            bookingService.deleteBookings(bookingIds);
            showDetails.setStatus(Constants.INACTIVE);
            return showsRepository.save(showDetails);
        }
        return null;
    }
}
