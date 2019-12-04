package org.tnmk.practicespringarangodb.pro02relationship

import org.slf4j.Logger

//import io.github.ganchix.arangodb.ArangoDBContainer

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.testcontainers.containers.wait.strategy.Wait
import org.tnmk.practicespringarangodb.common.arangodb.ArangoDBContainer
import org.tnmk.practicespringarangodb.pro02relationship.sample.entity.Person
import org.tnmk.practicespringarangodb.pro02relationship.sample.story.PersonService
import spock.lang.Shared

import java.time.Duration

class PersonServiceSpec extends BaseSpecification{
    public static final Logger logger = LoggerFactory.getLogger(PersonServiceSpec.class);
    @Shared static ArangoDBContainer arangoDBContainer = new ArangoDBContainer("3.2.0").withoutAuthentication()
//    .waitingFor(
//            Wait//.forHealthcheck().withStartupTimeout(Duration.ofSeconds(60))//forListeningPort()..withStartupTimeout(Duration.ofSeconds(60)) //forLogMessage("ready",1).withStartupTimeout(Duration.ofSeconds(60))
//                .forLogMessage("ready for",1).withStartupTimeout(Duration.ofSeconds(30))
//    )

    @Autowired
    PersonService sampleStory;

    def setupSpec(){
        arangoDBContainer.start();
        logger.info("Finish starting arangodDBContainer "+ arangoDBContainer.getDatabase());
    }

    def cleanupSpec(){
        arangoDBContainer.stop();
        logger.info("Finish stopping arangodDBContainer");
    }

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
