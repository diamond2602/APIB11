package get;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PokemonPojo;
import pojo.PokemonTypePojo;
import pojo.StarWarsCharactersPojo;
import pojo.StarWarsPojo;

import java.util.List;
import java.util.Map;

public class Pokemon {
    /*
    Get https://pokeapi.co/api/v2/pokemon
    Deserialize response with POJO
    Validate the count=1126
    Construct a new map of pokemon name(KEY), url(value)
     */
    @Before //hook
    public void setup() {
        RestAssured.baseURI = "https://pokeapi.co";
        RestAssured.basePath = "api/v2/pokemon";
    }

    @Test
    public void PokemonTest() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when().get() //"https://pokeapi.co/api/v2/pokemon"
                .then().statusCode(200).extract().response();

        PokemonPojo parsedResponse = response.as(PokemonPojo.class);

        Assert.assertEquals(1126, parsedResponse.getCount());

        List<PokemonTypePojo> results = parsedResponse.getResults();
        // System.out.println(results.get(0).getName());

        for (PokemonTypePojo character : results) {
            System.out.println(character.getName());
            System.out.println(character.getUrl());

        }


    }

    @Test
    public void PokemonTest2() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when().get() //"https://pokeapi.co/api/v2/pokemon"
                .then().statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
        String nexturl = jsonPath.getString("next");

        System.out.println(nexturl);

        String pokemon1Name = jsonPath.getString("results[0].name");
        System.out.println(pokemon1Name);

        List<Map<String, String>> resultsList = jsonPath.getList("results");
        for (Map<String, String> pokemon : resultsList) {
            System.out.println(pokemon.get("name"));
        }

    }

    @Test
    public void PokemonTest3() {
        RestAssured.given().header("Accept", "application/json").log().all()
                .when().get()
                .then().statusCode(200).body("count", Matchers.equalTo(1126))
                .and()
                .body("next", Matchers.is("https://pokeapi.co/api/v2/pokemon?offset=20&limit=20"))
                .log().body();
    }



}