package com.example.servingwebcontentmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = GreetingController.class)
class ServingWebContentMvcApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andExpect(content().string(containsString("Get your greeting")));
    }

    @Test
    public void testDefaultGreeting() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(content().string(containsString("Hello, World!")));
    }

    @Test
    public void testGreetingWithParameter() throws Exception {
        mockMvc.perform(get("/greeting").param("name", "Bob"))
                .andExpect(content().string(containsString("Hello, Bob!")));
    }

}
