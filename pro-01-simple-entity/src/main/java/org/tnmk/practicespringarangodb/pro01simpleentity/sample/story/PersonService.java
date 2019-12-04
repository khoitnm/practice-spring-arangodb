package org.tnmk.practicespringarangodb.pro01simpleentity.sample.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.Person;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.repository.PersonRepository;

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

    /**
     * @return in the result list, each {@link Person} will NOT have information of {@link Person#livingCity} and {@link Person#homeTown}, they are lazy loading.
     */
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    /**
     * @return in the result list, each {@link Person} WILL HAVE all related information such as {@link Person#livingCity} and {@link Person#homeTown}.
     */
    public Iterable<Person> findAllDetailPerson(){
        Iterable<Person> persons = personRepository.findAllDetailPersons();
        return persons;
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }
}
