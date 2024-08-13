package com.bnova.simulate;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Set;


public class SimulateTestProfile implements QuarkusTestProfile
{
	@Override
	public String getConfigProfile()
	{
		return "simulate";
	}

	@Override
	public Set<Class<?>> getEnabledAlternatives()
	{
		return Set.of();
	}


}
