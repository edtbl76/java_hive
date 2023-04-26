package com.example.vaultconfig;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VaultConfigTests {

    @Autowired
    VaultConfig vaultConfig;

    @Test
    public void shouldContainConfigurationProperties() {
        assertThat(vaultConfig.getUsername()).isNotEmpty();
        assertThat(vaultConfig.getPassword()).isNotEmpty();
    }
}
