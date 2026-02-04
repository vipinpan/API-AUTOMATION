package api.automation.methods;

import api.automation.bookings.BookingEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class AuthHelper {

    /**
     * @author - Vipin Pandey
     * Method to create authentication token
     * @return Response
     */
    public Response createToken() {
        baseURI = BookingEndpoints.auth_endpoint;
        String authPayload = "{\"username\":\"admin\",\"password\":\"password123\"}";
        
        Response response = given()
                .contentType(ContentType.JSON)
                .body(authPayload)
                .when()
                .post();
        return response;
    }

    /**
     * @author - Vipin Pandey
     * Method to get authentication token string
     * @return String token
     */
    public String getToken() {
        Response response = createToken();
        
        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("token");
        }
        return null;
    }
}
