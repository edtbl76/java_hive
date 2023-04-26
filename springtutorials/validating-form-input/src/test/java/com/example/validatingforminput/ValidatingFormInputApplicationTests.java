package com.example.validatingforminput;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ValidatingFormInputApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkPersonInfoWhenNameMissingNameThenFailure() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/")
                .param("age", "20");

        mockMvc.perform(createPerson)
                .andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenNameTooShortThenFailure() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/")
                .param("name", "E")
                .param("age", "45");

        mockMvc.perform(createPerson)
                .andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenAgeMissingThenFailure() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/")
                .param("name", "Ed");

        mockMvc.perform(createPerson)
                .andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenAgeTooYoungThenFailure() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/")
                .param("name", "Ed")
                .param("age", "17");

        mockMvc.perform(createPerson)
                .andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenValidRequestThenSuccess() throws Exception {

        MockHttpServletRequestBuilder createPerson = post("/")
                .param("name", "Ed")
                .param("age", "45");

        mockMvc.perform(createPerson)
                .andExpect(model().hasNoErrors());
    }

}
