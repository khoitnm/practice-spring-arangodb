package org.tnmk.practicespringarangodb.pro04embeddeditems;

import java.lang.invoke.MethodHandles;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.Content;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.ContentCollection;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.story.ContentCollectionStory;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.story.ContentStory;

@Profile("!componenttest")
@Service
public class Initiation {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private ContentStory contentStory;

    @Autowired
    private ContentCollectionStory contentCollectionStory;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        ContentCollection contentCollection = createContentCollection();
        Content content = createContent();
        contentCollectionStory.addContentToCollection(contentCollection.getId(), content);

        ContentCollection foundContentCollection = contentCollectionStory.findById(contentCollection.getId()).get();
        List<Content> foundContentItems = contentStory.findAll();
        logger.info("Found contentCollection" + foundContentCollection);
        logger.info("Found content" + foundContentItems);
    }

    private ContentCollection createContentCollection() {
        ContentCollection contentCollection = new ContentCollection();
        contentCollection.setName("ContentCollection" + contentCollection.getName());
        contentCollection = contentCollectionStory.create(contentCollection);
        return contentCollection;
    }

    private Content createContent() {
        Content content = new Content();
        content.setName("Content_" + System.nanoTime());
        content = contentStory.create(content);
        return content;
    }
}
