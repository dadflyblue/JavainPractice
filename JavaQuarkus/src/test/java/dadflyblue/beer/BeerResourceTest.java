package dadflyblue.beer;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
class BeerResourceTest {

  @Test
  void testGetBeersOk() {
    given()
            .when()
            .get("/beers")
            .then()
            .statusCode(200)
            .body("[0]", is(notNullValue()),
                    "[0].abv", isA(float.class));
  }
}