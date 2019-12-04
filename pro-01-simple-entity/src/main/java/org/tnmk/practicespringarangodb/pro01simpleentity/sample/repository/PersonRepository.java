package org.tnmk.practicespringarangodb.pro01simpleentity.sample.repository;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.stereotype.Repository;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.Person;

@Repository
public interface PersonRepository extends ArangoRepository<Person, String> {
//    FOR p IN person
//    FOR ht IN city
//    FOR lc IN city
//    FILTER p.homeTown == ht._id AND p.livingCity == lc._id
//    RETURN merge (p, {homeTown: ht}, {livingCity: lc})

    /**
     * When running the query directly in ArangoDB client, it works.
     * Running in Java app, it was also able to return JSON response in the debug log message of ArangoDB.
     * However, it cannot convert that JSON data into Java class.
     * @deprecated I still NOT able to find a solution for this.
     * @return
     */
    @Deprecated
    @Query(" FOR p IN person " +
            " FOR ht IN city " +
            " FOR lc IN city " +
            " FILTER p.homeTown == ht._id AND p.livingCity == lc._id" +
            " RETURN merge (p, {homeTown: ht}, {livingCity: lc})")
    Iterable<Person> findAllDetailPersons();
}
