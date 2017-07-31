package com.cz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ShenHuaJie
 * @version 2016年6月21日 上午9:50:58
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket platformApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		Contact contact = new Contact("jomalone_jia", "", "jo_malone@outlook.com");
		ApiInfo apiInfo = new ApiInfo("cz-admin", "cz-admin", "V0.0.1", "", contact, "", "");
		docket.apiInfo(apiInfo);
		docket.forCodeGeneration(true);
		return docket;
	}


}