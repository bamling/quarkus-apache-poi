package com.github.bamling.example;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import javax.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ExcelResourceTest {

    @Test
    public void testExcelEndpoint() {
        given()
            .when().get("/excel")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_OCTET_STREAM);
    }

}