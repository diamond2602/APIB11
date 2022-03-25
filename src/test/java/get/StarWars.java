package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class StarWars {

    /*
    Get https://swapi.dev/api/people
    Validate we have more than 0 female character in the response
     */
    @Test
    public  void FemaleCountTest(){
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when()
                .get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();


        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>(){});
    List<Map<String,Object>> listOfCharacters=(List<Map<String,Object>>) parsedResponse.get("results");
    int count=0;
    for (int i=0; i<listOfCharacters.size();i++){
      //getting every character
       Map<String, Object> charMap = listOfCharacters.get(i);
      //getting gender of every character
       if(charMap.get("gender").equals("female")){
           ++count;
       }
    }
        Assert.assertTrue(count>0);


    }

    //homework
    //validate the height of r2-d2 robot
}
