package org.tnmk.practicespringarangodb.pro01simpleentity;

import java.math.BigDecimal;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.common.util.IterableUtils;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.City;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.Person;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.PersonView;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.story.CityService;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.story.PersonService;

import java.lang.invoke.MethodHandles;
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
        Person person = createPerson();
        City homeTown = createCity();
        City livingCity = createCity();
        person.setLivingCity(livingCity);
        person.setHomeTown(homeTown);
        person = personService.update(person);

        logger.info("Finding persons: ----------------------------------------------------------------------");
        Iterable<Person> foundPersons = personService.findAll();
        logger.info(IterableUtils.toStringEachLine("List of Persons in system (excluding related entities' information): -------------------------------------", foundPersons));

        //This method will FAIL!!!
        Iterable<Person> foundDetailPersons = personService.findDetailPersons();
        logger.info(IterableUtils.toStringEachLine("List of Persons in system (including related entities' information): ", foundDetailPersons));

        //This method will WORK!
        Iterable<PersonView> foundDetailPersonViews = personService.findDetailPersonViews();
        logger.info(IterableUtils.toStringEachLine("List of Persons in system (including related entities' information): ", foundDetailPersonViews));

        City foundLivingCity = cityService.findById(livingCity.getId()).get();
        logger.info("Found living city: " + foundLivingCity);

        logger.info("In the above foundPersons, each Person has related City information.\n" +
                "However, in the foundLivingCity, there's no Persons list information.");

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

    private City createCity() {
        City city = constructCity();
        return cityService.create(city);
    }

    private City constructCity() {
        City city = new City();
        city.setName("Initiation City_" + System.nanoTime());
        city.setLatitude(BigDecimal.valueOf(System.nanoTime()));
        city.setLongitude(BigDecimal.valueOf(System.nanoTime()));
        return city;
    }


    private Person createPersonIfNotExist() {
        Iterator<Person> persons = personService.findAll().iterator();
        if (!persons.hasNext()) {
            Person person = createPerson();
            logger.info("There was no data yet, hence we created a new Person " + person);
            return person;
        } else {
            return persons.next();
        }
    }

    private Person createPerson() {
        Person person = constructPerson();
        return personService.create(person);
    }

    private Person constructPerson() {
        Person person = new Person();
        person.setFullName("Initiation data_" + System.nanoTime());
        person.setDateOfBirth(Instant.now());
        person = personService.create(person);
        return person;
    }

    private void printPersons(Iterable<Person> persons) {
        logger.info("All persons:");
        persons.forEach(item -> {
            logger.info(item.toString());
        });
    }
}
