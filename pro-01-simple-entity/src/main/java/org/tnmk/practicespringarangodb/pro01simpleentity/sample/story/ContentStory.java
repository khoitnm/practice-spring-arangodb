package org.tnmk.practicespringarangodb.pro01simpleentity.sample.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.ContentEntity;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.repository.ContentRepository;

@Service
public class ContentStory {
    private final ContentRepository contentRepository;

    @Autowired
    public ContentStory(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public ContentEntity create(ContentEntity contentEntity) {
        return contentRepository.save(contentEntity);
    }

    public Iterable<ContentEntity> findAll() {
        return contentRepository.findAll();
    }

}
