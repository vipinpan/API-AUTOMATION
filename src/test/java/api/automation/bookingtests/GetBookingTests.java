package api.automation.bookingtests;

import api.automation.methods.Booking;
import api.automation.pojo.Bookings;
import api.automation.pojo.BookingDates;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import java.util.Date;

import static org.hamcrest.Matchers.*;

public class GetBookingTests {

    private Booking booking = new Booking();

    @Test
    public void getAllBookingIds() {
        Response response = booking.getBookingIds();

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().body("$", not(empty()));
    }

    @Test
    public void getBookingByIdValid() {
        BookingDates bookingDates = new BookingDates(new Date(), new Date());
        Bookings newBooking = new Bookings("Test", "User", 150, true, bookingDates, "Test Needs");
        
        Response createResponse = booking.createBooking(newBooking);
        int bookingId = createResponse.jsonPath().getInt("bookingid");

        Response response = booking.getBookingById(bookingId);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().body("firstname", equalTo("Test"))
                .body("lastname", equalTo("User"))
                .body("totalprice", equalTo(150))
                .body("depositpaid", equalTo(true))
                .body("additionalneeds", equalTo("Test Needs"));
    }

    @Test
    public void getBookingByIdInvalid() {
        Response response = booking.getBookingById(999999);

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void getBookingByIdNegative() {
        Response response = booking.getBookingById(-1);

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void getBookingByIdZero() {
        Response response = booking.getBookingById(0);

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void getBookingResponseTime() {
        Response response = booking.getBookingIds();
        
        long startTime = System.currentTimeMillis();
        response = booking.getBookingIds();
        long endTime = System.currentTimeMillis();

        long responseTime = endTime - startTime;
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(responseTime < 3000, "Response time should be less than 3 seconds");
    }

    @Test
    public void verifyBookingDataStructure() {
        Response response = booking.getBookingIds();
        Assert.assertEquals(response.getStatusCode(), 200);
        
        if (response.jsonPath().getList("$").size() > 0) {
            int firstBookingId = response.jsonPath().getInt("[0].bookingid");
            Response bookingResponse = booking.getBookingById(firstBookingId);
            
            bookingResponse.then().body("$", hasKey("firstname"))
                    .body("$", hasKey("lastname"))
                    .body("$", hasKey("totalprice"))
                    .body("$", hasKey("depositpaid"))
                    .body("$", hasKey("bookingdates"))
                    .body("$", hasKey("additionalneeds"));
        }
    }
}
