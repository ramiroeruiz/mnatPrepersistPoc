package com.mnaut.poc.contact;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.ContextConfigurer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

@Slf4j
@MicronautTest
@ContextConfigurer
public class poctc {

    private static PostgreSQLContainer postgreSQLContainer;
    private static ApplicationContext context;


    @BeforeAll
    public static void setUp(){
        postgreSQLContainer =  new PostgreSQLContainer("postgres:11.1")
                .withDatabaseName("integration-tests-db")
                .withUsername("siapi")
                .withPassword("siapi");
        postgreSQLContainer.start();

        // Overwrite the mongodb.uri value from your configuration file
//        context = ApplicationContext.run(
//                Map.of("datasources.default.url",postgreSQLContainer.getJdbcUrl(),
//        "datasources.default.driverClassName", "org.testcontainers.jdbc.ContainerDatabaseDriver",
//        "datasources.default.username","siapi",
//        "datasources.default.password","siapi"));

        System.setProperty("DB_URL", postgreSQLContainer.getJdbcUrl());
        System.setProperty("DB_USERNAME", postgreSQLContainer.getUsername());
        System.setProperty("DB_PASSWORD", postgreSQLContainer.getPassword());
    }


    @Test
    public void  test(){
        log.debug("TEST: {}",postgreSQLContainer.toString());


        log.debug("TEST url: {}","${datasources.default.url}");
        log.debug("TEST driverClassName: {}","${datasources.default.driverClassName}");
        log.debug("TEST username: {}","${datasources.default.username}");
        log.debug("TEST password : {}","${datasources.default.password}");
    }

}
