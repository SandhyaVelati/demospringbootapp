package com.northcoders.demospringbootapp.service;

import com.northcoders.demospringbootapp.model.Coordinates;
import com.northcoders.demospringbootapp.model.GeoCodingsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GeoCodingService {
    public Map<String,Double> getCoordinates(String cityName){
        WebClient webClient = WebClient.builder()
                .baseUrl("https://geocoding-api.open-meteo.com/v1")
                .build();
        GeoCodingsResponse coOrdinatesList = webClient.get().uri(uriBuilder -> uriBuilder.path("/search")
                        .queryParam("name", cityName)
                        .build())
                .retrieve()
                .bodyToMono(GeoCodingsResponse.class)
                .block();
        if(coOrdinatesList != null){
         return Map.of("longitude",coOrdinatesList.results().getFirst().longitude(),"latitude",coOrdinatesList.results().getFirst().latitude());
        }
        return Map.of("latitude",0.0,"longitude",0.0);
    }
}
