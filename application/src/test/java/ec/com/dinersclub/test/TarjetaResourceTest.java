package ec.com.dinersclub.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class TarjetaResourceTest {
	
	private UUID c1 = UUID.randomUUID();
	private UUID c2 = UUID.randomUUID();
	private UUID c3 = UUID.randomUUID();
	
	@Test
    public void testAdd() {
        given()
                .body("{\"id\": \""+c1.toString()+"\", \"nombre\": \"Cliente 1\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/tarjetas")
                .then()
                .statusCode(201);

        given()
		        .body("{\"id\": \""+c2.toString()+"\", \"nombre\": \"Cliente 2\"}")
		        .header("Content-Type", MediaType.APPLICATION_JSON)
		        .when()
		        .post("/tarjetas")
		        .then()
		        .statusCode(201);
        
        given()
		        .body("{\"id\": \""+c3.toString()+"\", \"nombre\": \"Cliente 3\"}")
		        .header("Content-Type", MediaType.APPLICATION_JSON)
		        .when()
		        .post("/tarjetas")
		        .then()
		        .statusCode(201);
    }

    @Test
    public void testList() {
        given()
                .when().get("/tarjetas")
                .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "nombre", containsInAnyOrder("Cliente 1","Cliente 2","Cliente 3"));
    }

    @Test
    public void testRemove() {
        given()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .delete("/tarjetas/"+c1.toString())
                .then()
                .statusCode(200);
        
        given()
		        .header("Content-Type", MediaType.APPLICATION_JSON)
		        .when()
		        .delete("/tarjetas/"+c2.toString())
		        .then()
		        .statusCode(200);
        
        given()
		        .header("Content-Type", MediaType.APPLICATION_JSON)
		        .when()
		        .delete("/tarjetas/"+c3.toString())
		        .then()
		        .statusCode(200);
    }
    
}