package com.adf.irisina.library.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.adf.irisina.library.repo")
public class AppConfig {
}
