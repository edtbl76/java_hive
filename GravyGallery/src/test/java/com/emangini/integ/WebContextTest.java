package com.emangini.integ;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integ: HTTP Responses")
@Tag("integration")
class WebContextTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Check home gate (GET /)")
    void testHomePage() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check rate dog (POST /)")
    void testRatePage() throws Exception {
        mockMvc.perform(
                post("/")
                        .param("dogId", "1")
                        .param("stars", "1")
                        .param("comment",""))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Check rate dog (POST /) of non existing dog")
    void testRatePageDogNotAvailable() throws Exception {
            mockMvc.perform(
                    post("/")
                            .param("dogId", "0")
                            .param("stars", "1")
                            .param("comment",""))
                    .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Check rate dog (POST /) with bad parameters")
    void testRatePageNoParameters() throws Exception {
        mockMvc.perform(post("/")).andExpect(status().isBadRequest());
    }

}
