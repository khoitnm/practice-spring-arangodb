package org.tnmk.practicespringarangodb.pro03enumfield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro03enumfield.sample.entity.ContentEntity;
import org.tnmk.practicespringarangodb.pro03enumfield.sample.entity.ContentType;
import org.tnmk.practicespringarangodb.pro03enumfield.sample.story.ContentStory;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class Initiation {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private ContentStory contentStory;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        List<ContentEntity> contentEntities = contentStory.findAll();

        if (contentEntities.isEmpty()) {
            createContentWithEnum();
        }

        List<ContentEntity> afterCreationContentEntities = contentStory.findAll();
        logger.info("Found content" + afterCreationContentEntities);

    }

    private ContentEntity createContentWithEnum() {
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setContentType(ContentType.IMAGE);
        contentEntity = contentStory.create(contentEntity);
        return contentEntity;
    }
}
