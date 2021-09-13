package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Weather;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WeatherSpecification {

    public static Specification<Weather> filter(Date date, String[] city, String sort){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(date != null){
                predicates.add(criteriaBuilder.equal(root.get("date"), date));
            }
            if(city != null && city.length > 0){
                predicates.add(criteriaBuilder.equal(root.get("city"), city));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
