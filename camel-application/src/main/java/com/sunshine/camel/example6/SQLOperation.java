package com.sunshine.camel.example6;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SQLOperation {

	public static void main(String[] args) throws Exception {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://localhost:3306/camel");
		dataSource.setUser("root");
		dataSource.setPassword("root");

		SimpleRegistry registry = new SimpleRegistry();
		registry.put("myDataSource", dataSource);

		CamelContext context = new DefaultCamelContext(registry);
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("direct:start").to("jdbc:myDataSource").bean(new ResultHandler(), "printResult");
			}
		});
		context.start();
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "select * from customer");

	}

}
