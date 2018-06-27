package com.customersscheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

@EnableHypermediaSupport(type = HAL)
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
}
