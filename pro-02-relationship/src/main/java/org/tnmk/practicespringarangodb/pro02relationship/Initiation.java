package org.tnmk.practicespringarangodb.pro02relationship;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro02relationship.sample.entity.City;
import org.tnmk.practicespringarangodb.pro02relationship.sample.entity.Person;
import org.tnmk.practicespringarangodb.pro02relationship.sample.story.CityService;
import org.tnmk.practicespringarangodb.pro02relationship.sample.story.PersonService;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.time.Instant;

@Service
public class Initiation {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private PersonService personService;

    @Autowired
    private CityService cityService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Person person = createPersonIfNotExist();
        City homeCity = createCity();
        City workingCity = createCity();
        person.setHomeTown(homeCity);
        person.setWorkingCity(workingCity);
        person = personService.update(person);

        Iterable<Person> contentEntities = personService.findAll();
        printContentEntities(contentEntities);
    }

    private City createCityIfNotExist() {
        Iterator<City> cities = cityService.findAll().iterator();
        if (!cities.hasNext()) {
            City city = createCity();
            logger.info("There was no data yet, hence we created a new City" + city);
            return city;
        } else {
            return cities.next();
        }
    }

    private City createCity(){
        City city = constructCity();
        return cityService.create(city);
    }

    private City constructCity(){
        City city = new City();
        city.setName("Initiation City_"+System.nanoTime());
        city.setLatitude(BigDecimal.valueOf(System.nanoTime()));
        city.setLongitude(BigDecimal.valueOf(System.nanoTime()));
        return city;
    }


    private Person createPersonIfNotExist() {
        Iterator<Person> persons = personService.findAll().iterator();
        if (!persons.hasNext()) {
            Person person = new Person();
            person.setFullName("Initiation data_" + System.nanoTime());
            person.setDateOfBirth(Instant.now());
            person = personService.create(person);
            logger.info("There was no data yet, hence we created a new Person " + person);
            return person;
        } else {
            return persons.next();
        }
    }

    private void printContentEntities(Iterable<Person> persons) {
        logger.info("All persons:");
        persons.forEach(item -> {
            logger.info(item.toString());
        });
    }
}
