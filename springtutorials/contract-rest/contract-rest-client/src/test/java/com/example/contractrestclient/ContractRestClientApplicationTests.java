package com.example.contractrestclient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest
class ContractRestClientApplicationTests {

    @RegisterExtension
    public StubRunnerExtension stubRunner = new StubRunnerExtension()
            .downloadStub(
                    "com.example", "contract-rest-service", "0.0.1-SNAPSHOT", "stubs")
            .withPort(8000)
            .stubsMode(LOCAL);

    @Test
    public void get_person_from_service_contract() {

        // given
        RestTemplate restTemplate = new RestTemplate();

        // when:
        ResponseEntity<Person> response = restTemplate.getForEntity("http://localhost:8000/person/1", Person.class);

        // Then
        then(response.getStatusCodeValue()).isEqualTo(200);
        then(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1L);
        then(response.getBody().getName()).isEqualTo("Steve");
        then(response.getBody().getSurname()).isEqualTo("Martin");
    }
}
