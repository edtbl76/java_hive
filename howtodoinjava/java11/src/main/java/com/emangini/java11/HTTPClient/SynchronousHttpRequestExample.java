package com.emangini.java11.HTTPClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SynchronousHttpRequestExample {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response;
        try {
            String endpoint = "https://postman-echo.com/get";
            URI uri = URI.create(endpoint + "?foo1=bar1&foo2=bar2");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();
            response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Headers    : " + response.headers().allValues("content-type"));
        System.out.println("Body       : " + response.body());
    }
}
