package com.northcoders.demospringbootapp.controller;

import com.northcoders.demospringbootapp.service.GeoCodingService;
import com.northcoders.demospringbootapp.service.SunRiseAndSetTimesService;
import com.northcoders.demospringbootapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class DemoController {
    @Autowired
    private GeoCodingService geoCodingService;
    @Autowired
    private SunRiseAndSetTimesService sunRiseAndSetTimesService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello there!";
    }

    @GetMapping("/person")
    public List<Person> getPersonDetails(){
        return List.of(
                new Person("San",32,"san1@gmail.com","Hitachi","pizza")
                , new Person("Ron",33,"ron1@gmail.com","Bournemouth","Steak"));
    }

    @GetMapping("/coordinates")
    public Map<String,Double> getCityCoordinates(@RequestParam String cityName){
        return geoCodingService.getCoordinates(cityName);
    }

    @GetMapping("/sunriseAndSet")
    public Map<String,String> getSunRiseAndSetTimes(@RequestParam Double latitude, @RequestParam Double longitude){
        return sunRiseAndSetTimesService.getSunRiseAndSetTimings(latitude,longitude);
    }


    @GetMapping("/city/sunriseAndset")
    public Map<String,String> getSunRiseandSetInaCity(@RequestParam String city){
        Map<String, Double> coordinates = geoCodingService.getCoordinates(city);
        //"longitude",coOrdinatesList.results().getFirst().longitude(),"latitude"
        return sunRiseAndSetTimesService.getSunRiseAndSetTimings(coordinates.get("latitude"),coordinates.get("longitude"));
    }

}
