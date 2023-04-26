package com.example.blog

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val emangini = User("emangini", "Edward", "Mangini")
        val spring5Article = Article(
            "Spring Framework 5.0 goes GA", "Dear Spring Community", "Blah blah", emangini)
        val spring6Article = Article(
            "Spring Framework 6.0 goes GA", "Dear Spring Community", "Blah blah", emangini)

        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(spring5Article, spring6Article)
        mockMvc.perform(get("/api/article/").accept(APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].author.login").value(emangini.login))
            .andExpect(jsonPath("\$.[0].slug").value(spring5Article.slug))
            .andExpect(jsonPath("\$.[1].author.login").value(emangini.login))
            .andExpect(jsonPath("\$.[1].slug").value(spring6Article.slug))

    }

    @Test
    fun `List users`() {
        val emangini = User("emangini", "Ed", "Mangini")
        val vunderwood = User("vunderwood", "Vanessa", "Underwood")

        every { userRepository.findAll() } returns listOf(emangini, vunderwood)
        mockMvc.perform(get("/api/user/").accept(APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].login").value(emangini.login))
            .andExpect(jsonPath("\$.[1].login").value(vunderwood.login))
    }
}