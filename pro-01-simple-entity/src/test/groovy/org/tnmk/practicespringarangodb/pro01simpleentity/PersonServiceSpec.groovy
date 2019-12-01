package org.tnmk.practicespringarangodb.pro01simpleentity

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.Person
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.story.PersonService

@ActiveProfiles("componenttest")
class PersonServiceSpec extends BaseSpecification{

    @Autowired
    PersonService sampleStory;

    def 'can get Entity from DB after creating'(){
        given:
        Person sampleEntity = new Person(
                fullName: "Entity_"+System.nanoTime()
        );
        sampleStory.create(sampleEntity);

        when:
        Iterable<Person> contentEntities = sampleStory.findAll();

        then:
        assert contentEntities.iterator().hasNext();
    }
}
