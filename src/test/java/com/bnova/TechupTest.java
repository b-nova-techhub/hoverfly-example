package com.bnova;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
@QuarkusTestResource(value = HoverflyResource.class)
public class TechupTest
{

	@Test
	void testGetById()
	{
		given()
				.pathParam("id", "1")
				.when()
				.get("/techhub/{id}")
				.then()
				.statusCode(200)
				.contentType("application/json")
				.body("id", is("1"))
				.body("slug", is("tech-slug"))
				.body("name", is("Tech Name"))
				.body("content", is("Tech Content"))
				.body("description", is("Tech Description"))
				.body("author", is("Tech Author"));
	}

	@Test
	void testGetTest()
	{
		given()
				.when().get("/techhub/test")
				.then()
				.statusCode(200)
				.body("message", is("test"));
	}
}
