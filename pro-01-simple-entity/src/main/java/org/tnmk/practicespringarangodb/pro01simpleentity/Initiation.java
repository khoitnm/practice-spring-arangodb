package org.tnmk.practicespringarangodb.pro01simpleentity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.ContentEntity;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.story.ContentStory;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Service
public class Initiation {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private ContentStory contentStory;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Iterable<ContentEntity> contentEntities = contentStory.findAll();
        if (!contentEntities.iterator().hasNext()) {
            ContentEntity contentEntity = new ContentEntity();
            contentEntity.setName("Initiation data_" + System.nanoTime());
            contentEntity.setContentSize(BigInteger.valueOf(System.nanoTime()));
            contentEntity.setCreateDateTime(Instant.now());//In later sample modules, we can configure to create this dateTime automatically.

            contentStory.create(contentEntity);
            logger.info("There was no data yet, hence we created a new ContentEntity "+contentEntity);
        }
        contentEntities = contentStory.findAll();
        printContentEntities(contentEntities);
    }

    private void printContentEntities(Iterable<ContentEntity> contentEntities){
        logger.info("All Content entities: \n");
        contentEntities.forEach(item -> {
            logger.info(item.toString());
        });
    }
}
