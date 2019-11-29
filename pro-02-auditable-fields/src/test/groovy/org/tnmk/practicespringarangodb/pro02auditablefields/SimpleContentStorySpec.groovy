package org.tnmk.practicespringarangodb.pro02auditablefields

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro02auditablefields.sample.entity.ContentEntity
import org.tnmk.practicespringarangodb.pro02auditablefields.sample.repository.ContentRepository
import org.tnmk.practicespringarangodb.pro02auditablefields.sample.story.ContentStory

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


    def 'CreateEntity: createDateTime and Id is generated after creating'() {
        given:
        ContentEntity sampleEntity = new ContentEntity(
                name: "Entity_" + System.nanoTime()
        );
        sampleStory.create(sampleEntity);

        when:
        List<ContentEntity> contentEntities = contentRepository.findAll();
        ContentEntity contentEntity = contentEntities.get(0);

        then:
        contentEntity.getId() != null
        contentEntity.getCreatedDateTime() != null
    }

    def 'Updating Entity: updateDateTime and createDateTime is generated after updating'() {
        given:
        ContentEntity sampleEntity = new ContentEntity(
                name: "Entity_" + System.nanoTime()
        );
        ContentEntity createdEntity = sampleStory.create(sampleEntity);
        ContentEntity foundCreatedEntity = contentRepository.findById(createdEntity.getId()).get();


        when:
        BigInteger updatingContentSize = BigInteger.valueOf(System.currentTimeMillis());
        createdEntity.setContentSize(updatingContentSize);
        ContentEntity updatedEntity = sampleStory.update(createdEntity);

        ContentEntity foundUpdatedEntity = contentRepository.findById(updatedEntity.getId()).get();

        then:
        foundUpdatedEntity.getContentSize() != null
        !foundUpdatedEntity.getContentSize().equals(foundCreatedEntity.getContentSize());
        foundUpdatedEntity.getName().equals(foundCreatedEntity.getName());
        foundUpdatedEntity.getCreatedDateTime() != null
        foundUpdatedEntity.getUpdatedDateTime() != null
    }
}
