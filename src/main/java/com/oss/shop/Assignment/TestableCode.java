package com.oss.shop.Assignment;

import java.util.List;
import java.lang.reflect.Method;

public class TestableCode {
	
	private TestableCode config;
	
	@FunctionalInterface
	interface Lambda {
		public void run(GslConfig config);
	}

	public CommandLineRunner registerStartersAndReaders(final Vertx vertx,
			final SpringVerticleFactory springVerticleFactory, final SpringUtil springUtil, final GslConfig gslConfig) {
		return args -> {
			// Scan all the beans annotated with the
			// @ElasticsearchBatchDataListener annotation.
			List<Pair<Object, Method>> listenerMethods = springUtil.getListenerMethods();

			// Deploy the starters per listener.
			deployVerticle(listenerMethods,
					jsonConfig -> deployStarterVerticle(vertx, springVerticleFactory, jsonConfig), config);

			// Deploy the reader verticles.
			deployVerticle(listenerMethods,
					jsonConfig -> deployReaderVerticle(vertx, springVerticleFactory, jsonConfig), config);
			setupTriggers(vertx, listenerMethods, config);
		};
	}
	
	private void setupTriggers(Vertx vertx, List<Pair<Object, Method>> listenerMethods, TestableCode config) {
		System.out.println("setupTriggers being executed");
	}
	

	private void deployVerticle(List<Pair<Object, Method>> listenerMethods, Lambda object, TestableCode config2) {
		System.out.println("deployVerticle being executed");
	}
	
	private void deployStarterVerticle(final Vertx vertx,
			final SpringVerticleFactory springVerticleFactory, final GslConfig gslConfig){
		System.out.println("deployStarterVerticle being executed");
	}
	
	private void deployReaderVerticle(final Vertx vertx,
			final SpringVerticleFactory springVerticleFactory, final GslConfig gslConfig){
		System.out.println("deployReaderVerticle being executed");
	}

}


interface CommandLineRunner{
	void run(String... args);
}

class SpringVerticleFactory{}

class Vertx{}

class SpringUtil{
	<K,V> List<Pair<K,V>> getListenerMethods(){return null;};
}

class GslConfig{}

class Pair<K,V>{}