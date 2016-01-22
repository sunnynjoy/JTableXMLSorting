package com.joy.jT.config;

import javax.inject.Singleton;
import com.joy.jT.bl.BusinessFunction;
import com.joy.jT.bl.BusinessFunctionImpl;
import com.google.inject.AbstractModule;

@Singleton
public class DependencyInjection extends AbstractModule{

	@Override
	protected void configure() {
		bind(BusinessFunction.class).to(BusinessFunctionImpl.class);
	}
}
