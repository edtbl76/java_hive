package com.example.managingtransactions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@AllArgsConstructor
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

    private final BookingService bookingService;

    @Override
    public void run(String... args) throws Exception {
        bookingService.book("Ed", "Connor", "Mike");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "First booking should work with no problem");

        log.info("Ed, Connor and Mike have been booked");

        // Fail Because Name is Too Long
        try {
            bookingService.book("Vanessa", "Gravy");
        } catch (RuntimeException ex) {
            log.info("v--- The following exception is expected because 'Vanessa' is too large for the varchar" +
                    "field in the H2 Database---v");
            log.error(ex.getMessage());
        }

        for (String person : bookingService.findAllBookings()) {
            log.info("So far, " + person + " is booked");
        }
        log.info("Neither Vanessa or Gravy should be present, because Vanessa violated the DB constraints " +
                "and Gravy was rolled back in the same TXN");

        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "'Vanessa', should have triggered a rollback");

        // Fail Because null entry
        try {
            bookingService.book("Gravy", null);
        } catch (RuntimeException ex) {
            log.info("v--- The following exception is expected because null isn't a valid value for the DB ---v");
            log.error(ex.getMessage());

        }

        for (String person : bookingService.findAllBookings()) {
            log.info("So far, " + person + " is booked");
        }
        log.info("We shouldn't see Gravy or null, because null violated the DB constrants and Gravy was once again " +
                "rolled back in the same TXN");

        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "'null', should have triggered a rollback");

    }
}
