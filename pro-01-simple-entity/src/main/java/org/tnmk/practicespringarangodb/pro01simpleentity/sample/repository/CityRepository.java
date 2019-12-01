package org.tnmk.practicespringarangodb.pro01simpleentity.sample.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import org.springframework.stereotype.Repository;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.City;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.Person;

@Repository
public interface CityRepository extends ArangoRepository<City, String> {
}
