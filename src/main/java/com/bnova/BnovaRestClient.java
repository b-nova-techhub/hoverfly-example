package com.bnova;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/techhub")
@RegisterRestClient
public interface BnovaRestClient
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Techup getById(@QueryParam("id") String id);

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	String getTest();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<Techup> getAll();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Techup create(Techup techup);

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Techup update(@PathParam("id") String id, Techup techup);

	@DELETE
	@Path("/{id}")
	void delete(@PathParam("id") String id);
}
