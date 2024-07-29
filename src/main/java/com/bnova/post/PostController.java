package com.bnova.post;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;


@Path("/posts")
public class PostController
{

	@RestClient
	JsonPlaceholderClient jsonPlaceholderClient;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Post getPost(@PathParam("id") int id)
	{
		return jsonPlaceholderClient.getPostById(id);
	}
}
