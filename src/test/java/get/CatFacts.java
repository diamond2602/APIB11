package get;

import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class CatFacts {

    /**
     get https://cat-fact.herokuapp.com/facts
     Print out only facts
     */

    @Test
    public  void catFactsTest(){
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when()
                .get("https://cat-fact.herokuapp.com/facts")
                .then().statusCode(200).extract().response();

        List<Map<String,Object>> catFactsList= response.as(new TypeRef<List<Map<String, Object>>>() {});

        ///List<String> list=> list.get(i) =>String
        for (int i=0; i<catFactsList.size(); i++){
            Map<String,Object> catFactMap= catFactsList.get(i);
            System.out.println(catFactMap.get("text"));
        }
    }

    @Test
    public  void lastFactsTest(){
        // Get https://cat-fact.herokuapp.com/facts
        //validate that the last fact equals to:
        //Cats are the most popular pet in the United States: There are 88 million pet cats and 74 million dogs.
      String expectedLastFact="Cats are the most popular pet in the United States: There are 88 million pet cats and 74 million dogs.";
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when()
                .get("https://cat-fact.herokuapp.com/facts")
                .then().statusCode(200).extract().response();

        List<Map<String,Object>> parsedResponse= response.as(new TypeRef<List<Map<String, Object>>>() {});

         Map<String,Object> lastFact =  parsedResponse.get(parsedResponse.size()-1);
      String actualLastFact= lastFact.get("text").toString();
        Assert.assertEquals(expectedLastFact,actualLastFact);


    }

    //homework
  //  get only verified true

}
