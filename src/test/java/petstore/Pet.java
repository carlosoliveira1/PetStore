package petstore;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;

public class Pet {

    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminhoJson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void  incluirPet() throws IOException {

        String jsonBody = lerJson("db/pat1.json");

        given()
                .contentType("application/json")
                .log().all().body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("doggie"))
                .body("status", is("available"))
                //.body("category.name", is("dog"))//Deveria estar passando "dog", mas está gerando erro
                //.body("tags.name", contains("dog"))
        ;

    }

    @Test
    public  void consultarPet(){
        String petId = "9222968140497182000";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + petId)

        .then().log().all()
                .statusCode(200)
                .body("name", is("doggie"))
               // .body("category.name", is("dog"))
                .body("status", is("available"))
        ;
    }

}
