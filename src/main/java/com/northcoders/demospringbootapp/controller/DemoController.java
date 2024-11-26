package com.northcoders.demospringbootapp.controller;

import com.northcoders.demospringbootapp.controller.service.GeoCodingService;
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
}
