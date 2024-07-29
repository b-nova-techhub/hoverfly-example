package com.bnova.capture;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.specto.hoverfly.junit.core.Hoverfly;

import java.nio.file.Paths;
import java.util.Map;

import static io.specto.hoverfly.junit.core.HoverflyConfig.localConfigs;
import static io.specto.hoverfly.junit.core.HoverflyMode.CAPTURE;


public class HoverflyCaptureResource implements QuarkusTestResourceLifecycleManager
{

	private Hoverfly hoverfly;

	@Override
	public Map<String, String> start()
	{
		hoverfly = new Hoverfly(localConfigs().proxyLocalHost(), CAPTURE);
		hoverfly.start();
		return null;
	}

	@Override
	public void stop()
	{
		if (hoverfly != null)
		{
			hoverfly.exportSimulation(Paths.get("src/test/resources/hoverfly/captured-simulation.json"));
			hoverfly.close();
		}
	}
}
