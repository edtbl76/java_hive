package com.example.accessingvault;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponse;
import org.springframework.vault.support.VaultResponseSupport;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.vault.core.VaultKeyValueOperationsSupport.KeyValueBackend.KV_2;

@SpringBootTest
class AccessingVaultApplicationTests {

    @Autowired
    private VaultOperations vaultOperations;



    @Test
    public void readShouldRetrieveVaultData() {

        VaultResponse response = this.vaultOperations.opsForKeyValue("secret", KV_2).get("github");
        assertThat(Objects.requireNonNull(response).getData()).containsEntry("github.oauth2.key", "secret");

    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void writeShouldStoreVaultData() {

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "ed");
        credentials.put("password", "12345");

        this.vaultOperations.opsForKeyValue("secret", KV_2).put("database", credentials);

        VaultResponseSupport<Credentials> mappedCreds = this.vaultOperations.opsForKeyValue("secret", KV_2)
                .get("database", Credentials.class);

        assertThat(mappedCreds.getData().getUsername()).isEqualTo("ed");
        assertThat(mappedCreds.getData().getPassword()).isEqualTo("12345");



    }




    @SuppressWarnings("unused")
    static class Credentials {

        private String username;
        private String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
