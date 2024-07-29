package com.bnova.spy;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
@QuarkusTestResource(value = HoverflySpyResource.class)
public class TechupSpyTest
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
				.contentType("application/json")
				.body("userId", is(1))
				.body("id", is(1))
				.body("title", is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
				.body("body", is("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));
	}

	@Test
	void testGetPostByIdReal()
	{
		given()
				.pathParam("id", 2)
				.when()
				.get("/posts/{id}")
				.then()
				.statusCode(200)
				.contentType("application/json")
				.body("id", is(2))
				.body("title", is("qui est esse")); // Adjust this based on the actual response from the real service
	}
}
