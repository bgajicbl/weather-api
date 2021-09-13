package com.hackerrank.weather.controller;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RequestMapping("/weather")
@RestController
public class WeatherApiRestController {

    @Autowired
    WeatherService weatherService;

    @GetMapping
    public  ResponseEntity<List<Weather>> getRecords(@RequestParam(required = false) String date,
                                                     @RequestParam(required = false) String[] city,
                                                     @RequestParam(required = false) String sort){

        List<Weather> output = weatherService.findFiltered(date, city, sort);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Weather> createRecord(@Valid @RequestBody Weather input){
        Weather output = weatherService.save(input);

        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Weather> getSingleRecord(@NotNull @PathVariable Integer id){
        Weather output = weatherService.findById(id);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
