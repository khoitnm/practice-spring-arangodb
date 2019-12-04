package org.tnmk.practicespringarangodb.pro02relationship.sample.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro02relationship.sample.entity.Person;
import org.tnmk.practicespringarangodb.pro02relationship.sample.repository.PersonRepository;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }
}
