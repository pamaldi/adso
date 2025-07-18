package cloud.isaura.adso.core.interfaces.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ExampleResourceTest
{
    @Test
    void testHelloEndpoint()
    {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Stat rosa pristine nomine, nomina nuda tenemus"));
    }

}