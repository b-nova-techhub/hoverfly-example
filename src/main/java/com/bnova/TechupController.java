package com.bnova;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Techup> getAll()
	{
		return restClient.getAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Techup create(Techup techup)
	{
		return restClient.create(techup);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Techup update(@PathParam("id") String id, Techup techup)
	{
		return restClient.update(id, techup);
	}
}
