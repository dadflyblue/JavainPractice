package dadflyblue.product;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class ProductResourceTest {

  @Test
  public void testGetFirstPageOfProductsOk() {
    given()
            .queryParam("pageIndex", 1)
            .queryParam("pageSize", 5)
            .when().get("/products")
            .then()
            .statusCode(200)
            .body("list", is(notNullValue()),
                    "pageCount", is(not(0)));
  }

  @Test
  public void testGetFirstPageOfProductsWithCategoryOk() {
    given()
            .queryParam("pageIndex", 1)
            .queryParam("pageSize", 5)
            .queryParam("category", "toy")
            .when().get("/products")
            .then()
            .statusCode(200)
            .body("list", is(notNullValue()),
                    "pageCount", is(not(0)));
  }

  @Test
  public void testGetOneProductOk() {
    given()
            .pathParam("id", 2)
            .when().get("/products/{id}")
            .then()
            .statusCode(200)
            .body("id", is(2),
                    "name", is("Tiger Toy"));
  }

  @Test
  public void testCreateOneProductOk() {
    given()
            .body(Product.newProduct("Tiger Toy2", 10_000L, "toy", 3,null))
            .contentType(ContentType.JSON)
            .when().put("/products")
            .then()
            .statusCode(200)
            .body("id", is(notNullValue()),
                    "name", is("Tiger Toy2"));
  }

  @Test
  public void testUpdateOneProductOk() {
    given()
            .body(Product.newProduct("ZOROBOT", 10_000L, "toy", 3, 2L))
            .contentType(ContentType.JSON)
            .when().post("/products")
            .then()
            .statusCode(200);

    given()
            .pathParam("id", 2)
            .when().get("/products/{id}")
            .then()
            .statusCode(200)
            .body("id", is(2),
                    "price", is(10_000));
  }

  @Test
  public void testDeleteOneProductOk() {
    given()
            .pathParam("id", 3)
            .when().delete("/products/{id}")
            .then()
            .statusCode(200);

    given()
            .pathParam("id", 3)
            .when().get("/products/{id}")
            .then()
            .statusCode(404);
  }

}
