package org.tnmk.practicespringarangodb.pro02relationship.sample.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import org.springframework.stereotype.Repository;
import org.tnmk.practicespringarangodb.pro02relationship.sample.entity.Person;

@Repository
public interface PersonRepository extends ArangoRepository<Person, String> {
}
