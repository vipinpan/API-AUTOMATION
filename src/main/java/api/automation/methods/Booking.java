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
    public Response updateBooking(int id,Bookings updatedbookings){
        baseURI = BookingEndpoints.booking_endpoint;
        Response response = given()
                .pathParam("id",id)
                .contentType(ContentType.JSON)
                .body(updatedbookings)
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
        Response response = given()
                .pathParam("id", id)
                .when()
                .delete("/{id}");
        return response;
    }
}
