package com.ermao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author Ermao
 * Date: 2021/9/20 19:17
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private Environment environment;

	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	private ApiInfo getApiInfo() {
		Contact contact = new Contact("ermao", "http://www.sayfeng.com", "yangshifeng19@qq.com");
		return new ApiInfo("Ermao's Api Documentation",
				"This is a cool documentation",
				"1.0",
				"http://www.sayfeng.com",
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList());
	}

	// 配置 Swagger 的 Docket 实例
	@Bean
	public Docket getDocket1() {
		Profiles profiles = Profiles.of("dev");
		boolean flag = environment.acceptsProfiles(profiles);
		System.out.println("================ " + flag);

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.groupName("ermao")
				.enable(flag)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ermao.controller"))
				.build();
	}

	@Bean
	public Docket getDocket2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("sanmao");
	}


}
