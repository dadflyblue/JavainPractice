package dadflyblue.fruit;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
class FruitResourceTest {

  @Test
  public void testFindAllFruitsOk() {
    given()
            .when().get("/fruits")
            .then()
            .statusCode(200)
            .body("[0]", is(notNullValue()));
  }

  @Test
  public void testFindSummerFruitsOk() {
    given()
            .queryParam("season", "Summer")
            .when().get("/fruits")
            .then()
            .statusCode(200)
            .body("[0]", is(notNullValue()),
                    "[0].season", is("Summer"));
  }

  @Test
  public void testCreateNewFruitOk() {
    given()
            .contentType(ContentType.JSON)
            .body(Fruit.of("Pineapple", "Summer"))
            .when().post("/fruits")
            .then()
            .statusCode(201)
            .body("name", is("Pineapple"));
  }
}