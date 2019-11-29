package org.tnmk.practicespringarangodb.pro04embeddeditems

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.Content
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.ContentCollection
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.repository.ContentRepository
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.story.ContentCollectionStory
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.story.ContentStory

/**
 * I know we can use == instead of equals(), don't need ";"at the end of a statement, etc.
 * However, I still prefer it because may be at some point, we may want to copy some code to a Java class.
 * We are still using Java code anyway.
 */
class SimpleContentStorySpec extends BaseSpecification {

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    private ContentStory contentStory;

    @Autowired
    private ContentCollectionStory contentCollectionStory;


    def 'addContentToCollection: Add existing Content into an existing ContentCollection'() {
        given:
        ContentCollection contentCollection = createContentCollection();
        Content content = createContent();


        when:
        contentCollectionStory.addContentToCollection(contentCollection.getId(), content);

        then:
        ContentCollection foundContentCollection = contentCollectionStory.findById(contentCollection.getId()).get();
        List<Content> foundContentItems = contentStory.findAll();

        assert foundContentCollection.getContentItems().size() == 1;
        assert foundContentCollection.getContentItems().get(0).getId() == content.getId();
        assert foundContentItems.stream().anyMatch{ item -> item.getId() == content.getId()}
    }

    def 'addContentToCollection: Add an existing Content twice will created 2 items inside ContentCollection'() {
        given:
        ContentCollection contentCollection = createContentCollection();
        Content content = createContent();


        when:
        contentCollectionStory.addContentToCollection(contentCollection.getId(), content);
        contentCollectionStory.addContentToCollection(contentCollection.getId(), content);

        then:
        ContentCollection foundContentCollection = contentCollectionStory.findById(contentCollection.getId()).get();
        List<Content> foundContentItems = contentStory.findAll();

        assert foundContentCollection.getContentItems().size() == 2;
        assert foundContentCollection.getContentItems().get(0).getId() == content.getId();
        assert foundContentItems.stream().anyMatch{ item -> item.getId() == content.getId()}
        assert foundContentCollection.getContentItems().get(0).getId() == foundContentCollection.getContentItems().get(1).getId();
    }

    def 'addContentToCollection: Add non-existing Content into an existing ContentCollection will also save that Content'() {
        given:
        int totalContentItems = 3;
        ContentCollection contentCollection = createContentCollection();
        List<Content> contentItems = constructContentItems(totalContentItems);


        when:
        for (Content content : contentItems) {
            contentCollectionStory.addContentToCollection(contentCollection.getId(), content);
        }

        then:
        ContentCollection foundContentCollection = contentCollectionStory.findById(contentCollection.getId()).get();
        List<Content> foundContentItems = contentStory.findAll();

        assert foundContentCollection.getContentItems().size() == totalContentItems;
        assert foundContentItems.stream().allMatch(){ item -> item.getId() != null}
    }

    def 'addUniqueContentToCollection: Add an existing Content twice only created one item inside ContentCollection'() {
        given:
        ContentCollection contentCollection = createContentCollection();
        Content content = createContent();


        when:
        contentCollectionStory.addUniqueContentToCollection(contentCollection.getId(), content);
        contentCollectionStory.addUniqueContentToCollection(contentCollection.getId(), content);

        then:
        ContentCollection foundContentCollection = contentCollectionStory.findById(contentCollection.getId()).get();
        List<Content> foundContentItems = contentStory.findAll();

        assert foundContentCollection.getContentItems().size() == 1;
        assert foundContentCollection.getContentItems().get(0).getId() == content.getId();
        assert foundContentItems.stream().anyMatch{ item -> item.getId() == content.getId()}
    }

    private ContentCollection createContentCollection() {
        ContentCollection contentCollection = new ContentCollection();
        contentCollection.setName("ContentCollection" + contentCollection.getName());
        contentCollection = contentCollectionStory.create(contentCollection);
        return contentCollection;
    }

    private Content createContent() {
        Content content = constructContent();
        content = contentStory.create(content);
        return content;
    }

    private Content constructContent() {
        Content content = new Content();
        content.setName("Content_" + System.nanoTime());
        return content;
    }

    private List<Content> constructContentItems(int totalItems) {
        List<Content> contentItems = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {
            Content content = constructContent();
            contentItems.add(content);
        }
        return contentItems;
    }
}
