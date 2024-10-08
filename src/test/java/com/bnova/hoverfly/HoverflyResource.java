package com.bnova.hoverfly;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.specto.hoverfly.junit.core.Hoverfly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import static io.specto.hoverfly.junit.core.HoverflyConfig.localConfigs;
import static io.specto.hoverfly.junit.core.HoverflyMode.SIMULATE;
import static io.specto.hoverfly.junit.core.HoverflyMode.SPY;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.equalsToJson;


public class HoverflyResource implements QuarkusTestResourceLifecycleManager
{
	private static final String APPLICATION_JSON = "application/json";
	private static final String TECHHUB = "/techhub";
	private static final String SERVICE_URL = "my-hoverfly-service";
	private static final String BASE_PATH = "src/test/resources/__files/";

	private Hoverfly hoverfly;

	@Override
	public Map<String, String> start()
	{
		var activeProfile = System.getProperty("quarkus.test.profile");

		if (StringUtils.equalsIgnoreCase(activeProfile, "simulate"))
		{
			hoverfly = new Hoverfly(localConfigs().destination(SERVICE_URL), SIMULATE);
			hoverfly.start();
			hoverfly.simulate(
					dsl(
							service(SERVICE_URL)
									.get(TECHHUB)
									.queryParam("id", "1")
									.willReturn(success(
											readJsonFile("example_get_by_id.json"),
											APPLICATION_JSON))

									.get(TECHHUB + "/test")
									.willReturn(success(
											"{\"message\":\"test\"}",
											APPLICATION_JSON))

									.get(TECHHUB)
									.willReturn(success(
											readJsonFile("example_get_all.json"),
											APPLICATION_JSON))

									.post(TECHHUB)
									.body(equalsToJson(readJsonFile("example_create_body.json")))
									.willReturn(success(
											readJsonFile("example_create_response.json"),
											APPLICATION_JSON))

									.put(TECHHUB + "/1")
									.body(equalsToJson(readJsonFile("example_update_body.json")))
									.willReturn(success(
											readJsonFile("example_update_response.json"),
											APPLICATION_JSON))

									.delete(TECHHUB + "/1")
									.willReturn(success("", APPLICATION_JSON))
					));

		}
		else if (StringUtils.equalsIgnoreCase(activeProfile, "spy"))
		{
			hoverfly = new Hoverfly(localConfigs().proxyLocalHost(), SPY);
			hoverfly.start();

			hoverfly.simulate(
					dsl(
							service("http://jsonplaceholder.typicode.com")
									.get("/posts/1")
									.willReturn(success(
											readJsonFile("example_spy_response.json"),
											APPLICATION_JSON))
					)
			);
		}


		return null;
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

	@Override
	public void stop()
	{
		String activeProfile = System.getProperty("quarkus.test.profile");
		if (hoverfly != null && "capture".equals(activeProfile))
		{
			hoverfly.exportSimulation(Paths.get("src/test/resources/hoverfly/captured-simulation.json"));
			hoverfly.close();
		}

		if (hoverfly != null)
		{
			hoverfly.close();
		}
	}
}
