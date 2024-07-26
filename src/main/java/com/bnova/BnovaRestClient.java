package com.bnova;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/techhub")
@RegisterRestClient
public interface BnovaRestClient
{
	@GET
	@Produces("application/json")
	Techup getById(@QueryParam("id") String id);

	@GET
	@Path("/test")
	@Produces("application/json")
	String getTest();
}
