package put;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PetPojo;
import post.Slack;

import java.util.Map;

public class Pet {
    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

    }

    @Test
    public void updatePetTest() {
        PetPojo pet = new PetPojo();
        pet.setName("pet from java");
        pet.setId(989065);
        pet.setStatus("funny");

        Response response = RestAssured.given()
                .accept(Slack.APPLICATION_JSON) //"application/json"
                .contentType(Slack.APPLICATION_JSON)
                .body(pet)
                .when().put()
                .then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        String name = String.valueOf(deserializedResponse.get("name"));
        Assert.assertEquals("pet from java", name);
        int id = (int) deserializedResponse.get("id");
        Assert.assertEquals(989065,id);
        String status = String.valueOf(deserializedResponse.get("status"));
        Assert.assertEquals("funny", status);

    }
}
