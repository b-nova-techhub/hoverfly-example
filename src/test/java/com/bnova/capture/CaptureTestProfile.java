package com.bnova.capture;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Set;


public class CaptureTestProfile implements QuarkusTestProfile
{
	@Override
	public String getConfigProfile()
	{
		return "capture";
	}

	@Override
	public Set<Class<?>> getEnabledAlternatives()
	{
		return Set.of();
	}


}
