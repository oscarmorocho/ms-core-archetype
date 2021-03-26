package ec.com.dinersclub.test;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;

import javax.ws.rs.core.MediaType;

@QuarkusTest
public class GrpcResourceTest {
	
	private UUID c1 = UUID.randomUUID();

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/grpc")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }
    
    /*@Test
    public void testAdd() {
        given()
                .body("{\"id\": \""+c1.toString()+"\", \"nombre\": \"Cliente 1\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/grpc/tarjetas")
                .then()
                .statusCode(201);
    }*/

}