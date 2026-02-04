package api.automation.bookingtests;

import api.automation.methods.Booking;
import api.automation.pojo.Bookings;
import api.automation.pojo.BookingDates;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import java.util.Date;

import static org.hamcrest.Matchers.*;

public class CreateBookingTests {

    private Booking booking = new Booking();

    @Test
    public void createBookingWithValidData() {
        BookingDates bookingDates = new BookingDates(new Date(), new Date());
        Bookings newBooking = new Bookings("John", "Doe", 100, true, bookingDates, "Breakfast");

        Response response = booking.createBooking(newBooking);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().body("booking.firstname", equalTo("John"))
                .body("booking.lastname", equalTo("Doe"))
                .body("booking.totalprice", equalTo(100))
                .body("booking.depositpaid", equalTo(true))
                .body("booking.additionalneeds", equalTo("Breakfast"));
    }

    @Test
    public void createBookingWithMinimalData() {
        BookingDates bookingDates = new BookingDates(new Date(), new Date());
        Bookings newBooking = new Bookings("Jane", "Smith", 50, false, bookingDates, null);

        Response response = booking.createBooking(newBooking);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().body("booking.firstname", equalTo("Jane"))
                .body("booking.lastname", equalTo("Smith"))
                .body("booking.totalprice", equalTo(50))
                .body("booking.depositpaid", equalTo(false));
    }

    @Test
    public void createBookingWithEmptyFirstName() {
        BookingDates bookingDates = new BookingDates(new Date(), new Date());
        Bookings newBooking = new Bookings("", "Doe", 100, true, bookingDates, "Breakfast");

        Response response = booking.createBooking(newBooking);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void createBookingWithHighPrice() {
        BookingDates bookingDates = new BookingDates(new Date(), new Date());
        Bookings newBooking = new Bookings("Luxury", "Guest", 10000, true, bookingDates, "VIP Service");

        Response response = booking.createBooking(newBooking);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().body("booking.totalprice", equalTo(10000));
    }

    @Test
    public void createBookingResponseTime() {
        BookingDates bookingDates = new BookingDates(new Date(), new Date());
        Bookings newBooking = new Bookings("Speed", "Test", 75, true, bookingDates, "Quick Check-in");

        long startTime = System.currentTimeMillis();
        Response response = booking.createBooking(newBooking);
        long endTime = System.currentTimeMillis();

        long responseTime = endTime - startTime;
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(responseTime < 5000, "Response time should be less than 5 seconds");
    }
}
