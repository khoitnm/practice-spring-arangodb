package org.tnmk.practicespringarangodb.pro01simpleentity.sample.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.City;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.Person;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.repository.CityRepository;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City create(City city){
        return cityRepository.save(city);
    }

    public Iterable<City> findAll(){
        return cityRepository.findAll();
    }
}
