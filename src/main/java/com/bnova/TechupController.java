package com.bnova;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RestClient;


@Path("/techhub")
public class TechupController
{

	@RestClient
	BnovaRestClient restClient;

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Techup id(@PathParam("id") String id)
	{
		return restClient.getById(id);
	}

	@GET
	@Path("/test")
	@Produces("application/json")
	public String test()
	{
		var techup = new Techup();
		techup.setId("1");
		techup.setSlug("tech-slug");
		techup.setName("Tech Name");
		techup.setAuthor("Tech Author");
		techup.setContent("Tech Content");

		return "{\"message\":\"test\"}";
	}
}
