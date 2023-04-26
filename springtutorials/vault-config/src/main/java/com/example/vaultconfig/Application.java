package com.example.vaultconfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VaultConfig.class)
public class Application implements CommandLineRunner {

    VaultConfig vaultConfig;

    public Application(VaultConfig vaultConfig) {
        this.vaultConfig = vaultConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) {
        System.out.println(vaultConfig);
        System.out.println("Username: " + vaultConfig.getUsername());
        System.out.println("Password: " + vaultConfig.getPassword());
    }
}
