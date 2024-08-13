package com.bnova.capture;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
@QuarkusTestResource(value = com.bnova.hoverfly.HoverflyResource.class)
@TestProfile(CaptureTestProfile.class)
public class PostCaptureTest
{
	@Test
	void testGetPostById()
	{
		given()
				.pathParam("id", 1)
				.when()
				.get("/posts/{id}")
				.then()
				.statusCode(200)
				.contentType(APPLICATION_JSON)
				.body("id", is(1))
				.body("userId", is(1))
				.body("title", is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
				.body("body", is("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));
	}
}
