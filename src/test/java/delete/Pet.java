package delete;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import utils.PayloadUtil;

public class Pet {
    int id = 1876540;
    String name = "hatiko";
    String status = "waiting";

    @Test
    public void deletePetTest(){
        //delete: https://petstore.swagger.io/v2/pet/989065
        RestAssured.given()
                .accept("application/json")
                .when().delete(String.valueOf(id))
                .then().statusCode(200);
    }

    @Before //hook
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(PayloadUtil.getPetPayload(id, name, status))
                .when().post()
                .then().statusCode(200);

        //delete: https://petstore.swagger.io/v2/pet/989065
        // POst: https://petstore.swagger.io/v2/pet
    }
}
