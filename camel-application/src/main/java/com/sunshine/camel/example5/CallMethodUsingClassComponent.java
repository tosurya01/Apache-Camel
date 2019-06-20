package com.sunshine.camel.example5;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CallMethodUsingClassComponent {

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("direct:start").to("class:com.sunshine.camel.example5.MyService?method=doSomething");
			}
		});
		context.start();
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "Hello");
	}

}
