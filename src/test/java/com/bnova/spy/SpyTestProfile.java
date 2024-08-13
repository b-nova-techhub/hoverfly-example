package com.bnova.spy;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Set;


public class SpyTestProfile implements QuarkusTestProfile
{
	@Override
	public String getConfigProfile()
	{
		return "spy";
	}

	@Override
	public Set<Class<?>> getEnabledAlternatives()
	{
		return Set.of();
	}
	
}
