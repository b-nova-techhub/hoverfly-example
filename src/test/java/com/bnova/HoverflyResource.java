package com.bnova;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.specto.hoverfly.junit.core.Hoverfly;

import java.util.Map;

import static io.specto.hoverfly.junit.core.HoverflyConfig.localConfigs;
import static io.specto.hoverfly.junit.core.HoverflyMode.SIMULATE;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;


public class HoverflyResource implements QuarkusTestResourceLifecycleManager
{

	private Hoverfly hoverfly;
	private static final String SERVICE_URL = "my-hoverfly-service";

	@Override
	public Map<String, String> start()
	{
		hoverfly = new Hoverfly(localConfigs().destination(SERVICE_URL), SIMULATE);
		hoverfly.start();
		hoverfly.simulate(
				dsl(
						service(SERVICE_URL)
								.get("/techhub")
								.queryParam("id", "1")
								.willReturn(success(
										"{\"id\":\"1\",\"slug\":\"tech-slug\",\"name\":\"Tech Name\",\"content\":\"Tech Content\",\"description\":\"Tech Description\",\"author\":\"Tech Author\"}",
										"application/json"))

								.get("/techhub/test")
								.willReturn(success(
										"{\"message\":\"test\"}",
										"application/json"))

								.get("/techhub")
								.willReturn(success(
										"[{\"id\":\"1\",\"slug\":\"tech-slug\",\"name\":\"Tech Name\",\"content\":\"Tech Content\",\"description\":\"Tech Description\",\"author\":\"Tech Author\"},{\"id\":\"2\",\"slug\":\"tech-slug2\",\"name\":\"Tech Name2\",\"content\":\"Tech Content2\",\"description\":\"Tech Description2\",\"author\":\"Tech Author2\"}]",
										"application/json"))

								.post("/techhub")
								.body("{\"slug\":\"new-slug\",\"name\":\"New Tech Name\",\"content\":\"New Tech Content\",\"description\":\"New Tech Description\",\"author\":\"New Tech Author\"}")
								.willReturn(success(
										"{\"id\":\"2\",\"slug\":\"new-slug\",\"name\":\"New Tech Name\",\"content\":\"New Tech Content\",\"description\":\"New Tech Description\",\"author\":\"New Tech Author\"}",
										"application/json"))

								.put("/techhub/1")
								.body("{\"id\":\"1\",\"slug\":\"updated-slug\",\"name\":\"Updated Tech Name\",\"content\":\"Updated Tech Content\",\"description\":\"Updated Tech Description\",\"author\":\"Updated Tech Author\"}")
								.willReturn(success(
										"{\"id\":\"1\",\"slug\":\"updated-slug\",\"name\":\"Updated Tech Name\",\"content\":\"Updated Tech Content\",\"description\":\"Updated Tech Description\",\"author\":\"Updated Tech Author\"}",
										"application/json"))
				));
		return null;
	}

	@Override
	public void stop()
	{
		hoverfly.close();
	}
}
