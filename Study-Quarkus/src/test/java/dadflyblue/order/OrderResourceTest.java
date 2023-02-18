package dadflyblue.order;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
class OrderResourceTest {

  @Test
  public void testCreateOrderOk() {
    given()
            .contentType(ContentType.JSON)
            .body(Order.newOrder(
                    Address.of(1L, "ShopRite of Hoboken", "900", "Madison St", "Hoboken", "07030"),
                    Set.of(OrderItem.of(2, 1), OrderItem.of(4, 1)),
                    "credit", 100L))
            .when()
            .post("/orders")
            .then()
            .statusCode(200)
            .body("id", is(notNullValue()),
                    "orderItems[0].order.id", is(notNullValue()));
  }
}