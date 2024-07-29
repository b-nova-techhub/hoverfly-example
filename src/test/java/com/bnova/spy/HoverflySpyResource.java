package com.bnova.spy;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.specto.hoverfly.junit.core.Hoverfly;

import java.util.Map;

import static io.specto.hoverfly.junit.core.HoverflyConfig.localConfigs;
import static io.specto.hoverfly.junit.core.HoverflyMode.SPY;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;


public class HoverflySpyResource implements QuarkusTestResourceLifecycleManager
{

	private Hoverfly hoverfly;

	@Override
	public Map<String, String> start()
	{
		hoverfly = new Hoverfly(localConfigs().proxyLocalHost(), SPY);
		hoverfly.start();
		hoverfly.simulate(
				dsl(
						service("jsonplaceholder.typicode.com")
								.get("/posts/1")
								.willReturn(success("{\"userId\":1,\"id\":1,\"title\":\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\"body\":\"quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto\"}", "application/json"))
				)
		);
		return null;
	}

	@Override
	public void stop()
	{
		if (hoverfly != null)
		{
			hoverfly.close();
		}
	}
}
