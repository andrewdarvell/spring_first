package ru.darvell.gb.spring;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
@EnableEurekaClient
public class Application {

    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/simple_app", "postgres", "postgres").load();
        flyway.migrate();
        SpringApplication.run(Application.class, args);
    }
}
///data/images/product/photo_2020-08-24_18-27-41.jpg
