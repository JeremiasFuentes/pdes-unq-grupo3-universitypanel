package pdes.c1.universitypanel.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@Configuration
@EnableJpaRepositories(basePackages = "pdes.c1.universitypanel.repositories", repositoryBaseClass = SimpleJpaRepository.class)
public class JpaConfig {}