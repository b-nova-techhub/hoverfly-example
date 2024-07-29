package com.bnova;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

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
				.contentType(MediaType.APPLICATION_JSON)
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

	@Test
	void testGetAll()
	{
		given()
				.when()
				.get("/techhub")
				.then()
				.statusCode(200)
				.contentType(MediaType.APPLICATION_JSON)
				.body("[0].id", is("1"))
				.body("[0].slug", is("tech-slug"))
				.body("[0].name", is("Tech Name"))
				.body("[0].content", is("Tech Content"))
				.body("[0].description", is("Tech Description"))
				.body("[0].author", is("Tech Author"))
				.body("[1].id", is("2"))
				.body("[1].slug", is("tech-slug2"))
				.body("[1].name", is("Tech Name2"))
				.body("[1].content", is("Tech Content2"))
				.body("[1].description", is("Tech Description2"))
				.body("[1].author", is("Tech Author2"));
	}

	@Test
	void testCreate()
	{
		String requestBody = "{\"slug\":\"new-slug\",\"name\":\"New Tech Name\",\"content\":\"New Tech Content\",\"description\":\"New Tech Description\",\"author\":\"New Tech Author\"}";

		given()
				.body(requestBody)
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.when()
				.post("/techhub")
				.then()
				.statusCode(200)
				.contentType(MediaType.APPLICATION_JSON)
				.body("id", is("2"))
				.body("slug", is("new-slug"))
				.body("name", is("New Tech Name"))
				.body("content", is("New Tech Content"))
				.body("description", is("New Tech Description"))
				.body("author", is("New Tech Author"));
	}

	@Test
	void testUpdate()
	{
		String requestBody = "{\"id\":\"1\",\"slug\":\"updated-slug\",\"name\":\"Updated Tech Name\",\"content\":\"Updated Tech Content\",\"description\":\"Updated Tech Description\",\"author\":\"Updated Tech Author\"}";

		given()
				.body(requestBody)
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.pathParam("id", "1")
				.when()
				.put("/techhub/{id}")
				.then()
				.statusCode(200)
				.contentType(MediaType.APPLICATION_JSON)
				.body("id", is("1"))
				.body("slug", is("updated-slug"))
				.body("name", is("Updated Tech Name"))
				.body("content", is("Updated Tech Content"))
				.body("description", is("Updated Tech Description"))
				.body("author", is("Updated Tech Author"));
	}
}
