package com.example.consumingwebservice;

import com.example.consumingwebservice.wsdl.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.consumingwebservice.wsdl.GetCountryRequest;

@Slf4j
public class CountryClient extends WebServiceGatewaySupport {

    public GetCountryResponse getCountry(String country) {

        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        log.info("Requesting location for " + country);

        String endpoint = "http://localhost:8080/ws/countries.wsdl";
        String callback = "http://spring.io/guides/gs-producing-web-service/GetCountryResponse";
        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/countries", request,
                        new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/GetCountryResponse"));

        return response;
    }
}
