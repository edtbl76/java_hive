package com.example.accessingvault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultSysOperations;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultTransitOperations;
import org.springframework.vault.support.VaultMount;
import org.springframework.vault.support.VaultResponse;

import static org.springframework.vault.core.VaultKeyValueOperationsSupport.KeyValueBackend.KV_2;

@SpringBootApplication
public class AccessingVaultApplication implements CommandLineRunner {

    private final VaultTemplate vaultTemplate;

    public AccessingVaultApplication(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccessingVaultApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        VaultResponse response = vaultTemplate
                .opsForKeyValue("secret", KV_2).get("github");

        System.out.println("Value of github.oauth2.key");
        System.out.println("--------------------------");
        System.out.println(response.getData().get("github.oauth2.key"));
        System.out.println("--------------------------");
        System.out.println();

        // Encrypt Some Data Using Transit Backend
        VaultTransitOperations transitOperations = vaultTemplate.opsForTransit();

        // Set up transit
        VaultSysOperations sysOperations = vaultTemplate.opsForSys();

        if (!sysOperations.getMounts().containsKey("transit/")) {
            sysOperations.mount("transit", VaultMount.create("transit"));
        }

        // Encrypt plain text value.
        String ciphertext = transitOperations.encrypt("foo-key", "Secure message");

        System.out.println("Encrypted value");
        System.out.println("----------------------------------");
        System.out.println(ciphertext);
        System.out.println("----------------------------------");
        System.out.println();

        // Decrypt
        String plaintext = transitOperations.decrypt("foo-key", ciphertext);

        System.out.println("Decrypted value");
        System.out.println("----------------------------------");
        System.out.println(plaintext);
        System.out.println("----------------------------------");
        System.out.println();



    }
}
