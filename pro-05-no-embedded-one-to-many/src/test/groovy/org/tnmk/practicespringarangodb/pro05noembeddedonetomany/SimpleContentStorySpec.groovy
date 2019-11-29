package org.tnmk.practicespringarangodb.pro05noembeddedonetomany

import org.apache.commons.lang3.builder.EqualsBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.entity.Content
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.entity.ContentCollection
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.repository.ContentRepository
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.story.ContentCollectionStory
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.story.ContentStory

import java.util.stream.Collectors

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


    def 'moveContentToCollection: Add a created Content into an existing ContentCollection'() {
        given:
        ContentCollection contentCollection = createContentCollection();
        Content content = createContent();

        when:
        contentCollectionStory.putContentIntoCollection(contentCollection.getId(), content.getId());

        then:
        ContentCollection foundContentCollection = contentCollectionStory.findById(contentCollection.getId()).get();
        List<Content> foundContentItemsInCollection = contentStory.findByCollectionId(foundContentCollection.getId());

        assert foundContentItemsInCollection.size() == 1;
        assert foundContentItemsInCollection.stream().anyMatch { item -> item.getId() == content.getId() }
    }

    def 'moveContentToCollection: Moving an existing Content many times does NOT create multiple items inside ContentCollection'() {
        given:
        ContentCollection contentCollection = createContentCollection();
        Content content = createContent();


        when:
        contentCollectionStory.putContentIntoCollection(contentCollection.getId(), content.getId());
        contentCollectionStory.putContentIntoCollection(contentCollection.getId(), content.getId());

        then:
        ContentCollection foundContentCollection = contentCollectionStory.findById(contentCollection.getId()).get();
        List<Content> foundContentItemsInCollection = contentStory.findByCollectionId(foundContentCollection.getId());

        assert foundContentItemsInCollection.size() == 1;
        assert foundContentItemsInCollection.stream().anyMatch { item -> item.getId() == content.getId() }
    }

    def 'moveContentToCollection: We can move a Content into a Collection BEFORE creating that Collection'() {
        given:
        String contentCollectionId = UUID.randomUUID().toString();
        Content content = createContent();

        when:
        contentCollectionStory.putContentIntoCollection(contentCollectionId, content.getId());
        contentCollectionStory.putContentIntoCollection(contentCollectionId, content.getId());

        ContentCollection contentCollection = createContentCollection();
        contentCollection.setId(contentCollectionId);
        ContentCollection savedContentCollection = contentCollectionStory.create(contentCollection);

        then:
        ContentCollection foundContentCollection = contentCollectionStory.findById(contentCollectionId).get();
        List<Content> foundContentItemsInCollection = contentStory.findByCollectionId(contentCollectionId);

        assert foundContentCollection != null;
        assert savedContentCollection.getId() == foundContentCollection.getId()

        assert foundContentItemsInCollection.size() == 1;
        assert foundContentItemsInCollection.stream().anyMatch { item -> item.getId() == content.getId() }
    }

    def 'findCollectionIncludingContentItems: Can find Collection with multiple child items'() {
        given:
        int totalItems = 5;
        ContentCollection contentCollection = createContentCollection();
        List<Content> contentItems = createContentItems(totalItems);
        for (Content content : contentItems) {
            contentCollectionStory.putContentIntoCollection(contentCollection.getId(), content.getId());
        }

        when:
        ContentCollection foundContentCollection = contentCollectionStory.findCollectionIncludingContentItems(contentCollection.getId()).get();

        then:
        List<Content> foundContentItemsInCollection = contentStory.findByCollectionId(foundContentCollection.getId());

        assert foundContentItemsInCollection.size() == totalItems;
        assert EqualsBuilder.reflectionEquals(foundContentCollection.getContentItems(), foundContentItemsInCollection)
    }

    private ContentCollection createContentCollection() {
        ContentCollection contentCollection = constructContentCollection();
        contentCollection = contentCollectionStory.create(contentCollection);
        return contentCollection;
    }

    private ContentCollection constructContentCollection() {
        ContentCollection contentCollection = new ContentCollection();
        contentCollection.setName("ContentCollection" + contentCollection.getName());
        return contentCollection;
    }

    private Content createContent() {
        Content content = constructContent();
        content = contentStory.create(content);
        return content;
    }

    private List<Content> createContentItems(int totalItems) {
        List<Content> contentItems = constructContentItems(totalItems);
        List<Content> savedContentItems = contentItems.stream()
                .map { content -> contentStory.create(content) }
                .collect(Collectors.toList());
        return savedContentItems;
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
