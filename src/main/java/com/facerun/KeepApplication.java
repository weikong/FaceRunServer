package com.facerun;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class KeepApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {
//public class KeepApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
//		SpringApplication.run(KeepApplication.class, args);
		SpringApplication application = new SpringApplication(KeepApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
//		SocketChatTest socketChatTest = new SocketChatTest();
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		configurableEmbeddedServletContainer.setPort(9090);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return super.configure(builder);
		return builder.sources(KeepApplication.class);
	}
}
