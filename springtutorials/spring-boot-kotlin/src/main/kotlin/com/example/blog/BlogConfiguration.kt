package com.example.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository, articleRepository: ArticleRepository) = ApplicationRunner {

        val emangini = userRepository.save(User("emangini", "Edward", "Mangini"))

        articleRepository.save(Article(
            title = "A New Hope",
            headline = "Episode IV",
            content = "Help me Obi Wan, you're my only hope",
            author = emangini
        ))

        articleRepository.save(Article(
            title = "Empire Strikes Back",
            headline = "Episode V",
            content = "Luke, I am your father",
            author = emangini
        ))
    }
}