package ru.netology.springBootDemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> appDev = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> appProd = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);


    @BeforeAll
    public static void setUp() {
        appDev.start();
        appProd.start();
    }

    @Test
    void contextLoads() {

        String devSuccessMsg = "Current profile is dev";
        String prodSuccessMsg = "Current profile is production";

        Integer devAppPort = appDev.getMappedPort(8080);
        Integer prodAppPort = appProd.getMappedPort(8081);

        ResponseEntity<String> entityDev = restTemplate.getForEntity("http://localhost:" + devAppPort + "/profile", String.class);
        System.out.println(entityDev.getBody());

        Assertions.assertEquals(entityDev.getBody(), devSuccessMsg);


        ResponseEntity<String> entityProd = restTemplate.getForEntity("http://localhost:" + prodAppPort + "/profile", String.class);
        System.out.println(entityProd.getBody());

        Assertions.assertEquals(entityProd.getBody(), prodSuccessMsg);
    }

}