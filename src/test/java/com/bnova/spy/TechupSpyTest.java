package com.bnova.spy;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
@QuarkusTestResource(value = com.bnova.hoverfly.HoverflyResource.class)
@TestProfile(SpyTestProfile.class)
public class TechupSpyTest
{

	@Test
	void testGetPostByIdMocked()
	{
		given()
				.pathParam("id", 1)
				.when()
				.get("/posts/{id}")
				.then()
				.statusCode(200)
				.contentType(APPLICATION_JSON)
				.body("id", is(1))
				.body("userId", is(99))  // This is the mocked userId
				.body("title", is("Mocked title"))
				.body("body", is("Mocked body content"));
	}

	@Test
	void testGetPostByIdFromRealService()
	{
		given()
				.pathParam("id", 2)
				.when()
				.get("/posts/{id}")
				.then()
				.statusCode(200)
				.contentType(APPLICATION_JSON)
				.body("id", is(2))
				.body("userId", is(1))  // corrected this line to match the real API response
				.body("title", is("qui est esse"))
				.body("body", is("est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"));
	}

	@Test
	void testGetPostByIdFromRealService2()
	{
		given()
				.pathParam("id", 3)
				.when()
				.get("/posts/{id}")
				.then()
				.statusCode(200)
				.contentType(APPLICATION_JSON)
				.body("id", is(3))
				.body("userId", is(1))
				.body("title", is("ea molestias quasi exercitationem repellat qui ipsa sit aut"))
				.body("body", is("et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut"));
	}

}
