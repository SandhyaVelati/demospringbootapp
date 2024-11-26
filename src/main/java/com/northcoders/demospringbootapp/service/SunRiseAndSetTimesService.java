package com.northcoders.demospringbootapp.service;

import com.northcoders.demospringbootapp.model.SunRiseAndSunSetResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class SunRiseAndSetTimesService {
    public Map<String,String> getSunRiseAndSetTimings(double latitude,double longitude){
        WebClient webClient =  WebClient.builder().baseUrl("https://api.sunrisesunset.io").build();
        SunRiseAndSunSetResponse sunRiseAndSunSetResponse = webClient.get().uri(uriBuilder -> uriBuilder.path("/json")
                        .queryParam("lat", latitude)
                        .queryParam("lng", longitude).build())
                .retrieve()
                .bodyToMono(SunRiseAndSunSetResponse.class)
                .block();
        if(sunRiseAndSunSetResponse != null){
            String sunRise = sunRiseAndSunSetResponse.results().sunrise();
            String sunSet = sunRiseAndSunSetResponse.results().sunset();
            return Map.of("sunset",sunSet,"sunrise",sunRise);
        }
        return Map.of("sunrise","not found","sunset","not found");
    }

}
