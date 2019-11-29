package org.tnmk.practicespringarangodb.pro03enumfield

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro03enumfield.sample.entity.ContentEntity
import org.tnmk.practicespringarangodb.pro03enumfield.sample.entity.ContentType
import org.tnmk.practicespringarangodb.pro03enumfield.sample.repository.ContentRepository
import org.tnmk.practicespringarangodb.pro03enumfield.sample.story.ContentStory

/**
 * I know we can use == instead of equals(), don't need ";"at the end of a statement, etc.
 * However, I still prefer it because may be at some point, we may want to copy some code to a Java class.
 * We are still using Java code anyway.
 */
class SimpleContentStorySpec extends BaseSpecification {

    @Autowired
    ContentStory sampleStory;

    @Autowired
    ContentRepository contentRepository;


    def 'CreateEntity: enum field is stored correctly'() {
        given:
        ContentEntity sampleEntity = new ContentEntity(
                name: "Entity_" + System.nanoTime(),
                contentType: ContentType.TEXT
        );
        ContentEntity savedEntity = sampleStory.create(sampleEntity);

        when:
        ContentEntity contentEntity = contentRepository.findById(savedEntity.getId()).get();

        then:
        contentEntity.getId() != null
        contentEntity.getContentType() == sampleEntity.getContentType()
    }
}
