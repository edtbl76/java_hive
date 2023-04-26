package com.example.consumingwebservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CountryConfiguration {

    private static final String CONTEXT_PATH = "com.example.consumingwebservice.wsdl";
    private static final String DEFAULT_URI = "http://localhost:8080/ws";

    /*
        We point the marshaller at the generated domain objects
        - this is how we serialize/deserialize between XML and POJOs.
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(CONTEXT_PATH);
        return marshaller;
    }

    /*
        This client maps the Country to the marshaller.
     */
    @Bean
    public CountryClient countryClient(Jaxb2Marshaller marshaller) {
        CountryClient client = new CountryClient();
        client.setDefaultUri(DEFAULT_URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
