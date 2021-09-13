package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer>, JpaSpecificationExecutor {

    List<Weather> findAllByOrderByIdAsc();
    List<Weather> findByDate(Date date);
    List<Weather> findByCityIgnoreCaseIn(String[] city);

}
