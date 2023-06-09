package com.example.handlingformsubmission;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(GreetingController.class)
@TestPropertySource(properties = "logging.level.org.springframework.web=DEBUG")
class HandlingFormSubmissionApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void rendersForm() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(content().string(containsString("Form")));
    }

    @Test
    public void submitsForm() throws Exception {
        mockMvc.perform(post("/greeting")
                .param("id", "12345").param("content", "hello"))
                .andExpect(content().string(containsString("Result")))
                .andExpect(content().string(containsString("id: 12345")));
    }

}
