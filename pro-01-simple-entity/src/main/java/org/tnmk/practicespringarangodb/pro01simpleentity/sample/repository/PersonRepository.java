package org.tnmk.practicespringarangodb.pro01simpleentity.sample.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import org.springframework.stereotype.Repository;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.Person;

@Repository
public interface PersonRepository extends ArangoRepository<Person, String> {
}
