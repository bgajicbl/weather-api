package com.hackerrank.weather.service;

import com.hackerrank.weather.exception.DateNotValidException;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.hackerrank.weather.repository.WeatherSpecification.filter;

@Service
@Log
public class WeatherService {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");


    @Autowired
    WeatherRepository weatherRepository;

    public Weather save(Weather input){
        return weatherRepository.save(input);
    }

    public Weather findById(Integer id){
        Optional<Weather> weatherOpt = weatherRepository.findById(id);
       return weatherOpt.orElseThrow(IllegalArgumentException::new);
    }

    public List<Weather> findFiltered(String dateStr, String[] cities, String sort){
        List<Weather> weatherList = new ArrayList<>();
        Date date = null;
        try {
            date = dateStr == null ? null : simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new DateNotValidException("Wrong date format");
        }
        if(cities != null && cities.length > 0)
            weatherList = weatherRepository.findByCityIgnoreCaseIn(cities);

        if (date == null && (cities == null || cities.length == 0)){
            weatherList = weatherRepository.findAllByOrderByIdAsc();
        }

        return weatherList;
    }
}
