package org.tnmk.practicespringarangodb.pro01simpleentity

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.ContentEntity
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.story.ContentStory

@ActiveProfiles("componenttest")
class SimpleContentStorySpec extends BaseSpecification{

    @Autowired
    ContentStory sampleStory;

//    @Autowired
//    ContentRepository contentRepository;


    def 'can get Entity from DB after creating'(){
        given:
        ContentEntity sampleEntity = new ContentEntity(
                name: "Entity_"+System.nanoTime()
        );
        sampleStory.create(sampleEntity);

        when:
        List<ContentEntity> contentEntities = sampleStory.findAll();

        then:
        !contentEntities.isEmpty()
    }
}
