package com.bnova.capture;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
@QuarkusTestResource(value = com.bnova.capture.HoverflyCaptureResource.class)
public class PostCaptureTest
{
	private static final String BASE_PATH = "src/test/resources/__files/";
	private static final ObjectMapper objectMapper = new ObjectMapper();

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
				.body("id", is(1))
				.body("userId", is(1))
				.body("title", is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
				.body("body", is("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));
	}

	private String readJsonFile(String path)
	{
		try
		{
			return Files.readString(Paths.get(BASE_PATH + path));
		}
		catch (IOException e)
		{
			throw new RuntimeException("Failed to read JSON file: " + path, e);
		}
	}
}
