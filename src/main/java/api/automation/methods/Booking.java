package api.automation.methods;

import api.automation.bookings.BookingEndpoints;
import api.automation.pojo.Bookings;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Booking {

    /**
     * @author - Vipin Pandey
     * Method to get booking for all ids
     * @return Response
     */
    public Response getBookingIds(){
        baseURI = BookingEndpoints.booking_endpoint;
        Response response = given()
                .when()
                .get();
        return response;
    }

    /**
     * @author - Vipin Pandey
     * Method to get booking id by ids
     * @return Response
     */
    public Response getBookingById(int id){
        baseURI = BookingEndpoints.booking_endpoint;
        Response response = given()
                .pathParam("id",id)
                .when()
                .get("/{id}");
        return response;
    }

    /**
     * @author - Vipin Pandey
     * Method to create booking ids
     * @return Response
     */
    public Response createBooking(Bookings bookings){
        baseURI = BookingEndpoints.booking_endpoint;
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bookings)
                .when()
                .post();
        return response;
    }

    /**
     * @author - Vipin Pandey
     * Method to update booking details
     * @return Response
     */
    public Response updateBooking(int id, Bookings updatedbookings){
        baseURI = BookingEndpoints.booking_endpoint;
        AuthHelper authHelper = new AuthHelper();
        String token = authHelper.getToken();
        
        // Build JSON manually to ensure proper date formatting
        String jsonPayload = String.format(
            "{\"firstname\":\"%s\",\"lastname\":\"%s\",\"totalprice\":%d,\"depositpaid\":%b,\"bookingdates\":{\"checkin\":\"%s\",\"checkout\":\"%s\"},\"additionalneeds\":\"%s\"}",
            updatedbookings.getFirstname(),
            updatedbookings.getLastname(),
            updatedbookings.getTotalprice(),
            updatedbookings.isDepositpaid(),
            updatedbookings.getBookingdates().getCheckinString(),
            updatedbookings.getBookingdates().getCheckoutString(),
            updatedbookings.getAdditionalneeds() != null ? updatedbookings.getAdditionalneeds() : ""
        );
        
        Response response = given()
                .relaxedHTTPSValidation()
                .pathParam("id",id)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(jsonPayload)
                .when()
                .put("/{id}");
        
        return response;
    }

    /**
     * @author - Vipin Pandey
     * Method to delete booking ids
     * @return Response
     */
    public Response deleteBooking(int id){
        baseURI = BookingEndpoints.booking_endpoint;
        AuthHelper authHelper = new AuthHelper();
        String token = authHelper.getToken();
        
        Response response = given()
                .pathParam("id", id)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/{id}");
        return response;
    }
}
