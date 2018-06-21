package com.oss.shop.Assignment;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.BDDMockito.*;

public class TestableCodeTest {
	
	SpringUtil springUtil = mock(SpringUtil.class);
	Vertx vertx = mock(Vertx.class);
	SpringVerticleFactory springVerticleFactory = mock(SpringVerticleFactory.class);
	GslConfig gslConfig = mock(GslConfig.class);
	TestableCode config = new TestableCode();
	
	@Test
	public void testRegisterStartersAndReaders() {
		when(springUtil.getListenerMethods()).thenReturn(null);
        CommandLineRunner runner = config.registerStartersAndReaders(vertx, springVerticleFactory, springUtil, gslConfig);
        assertNotNull(runner);
        runner.run("Hello World");
	}

}
