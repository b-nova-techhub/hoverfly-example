package com.bnova.post;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/posts")
@RegisterRestClient
public interface JsonPlaceholderClient
{
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Post getPostById(@PathParam("id") int id);
}
