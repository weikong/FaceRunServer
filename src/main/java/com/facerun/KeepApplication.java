package com.facerun;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class KeepApplication implements EmbeddedServletContainerCustomizer {

	public static void main(String[] args) {
//		SpringApplication.run(KeepApplication.class, args);
		SpringApplication application = new SpringApplication(KeepApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		configurableEmbeddedServletContainer.setPort(80);
	}
}
