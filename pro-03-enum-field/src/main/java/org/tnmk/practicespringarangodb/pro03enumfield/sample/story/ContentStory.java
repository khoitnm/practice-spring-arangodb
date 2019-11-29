package org.tnmk.practicespringarangodb.pro03enumfield.sample.story;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro03enumfield.sample.entity.ContentEntity;
import org.tnmk.practicespringarangodb.pro03enumfield.sample.repository.ContentRepository;

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
    public ContentEntity update(ContentEntity contentEntity) {
        return contentRepository.save(contentEntity);
    }
    public Optional<ContentEntity> findById(String id){
        return contentRepository.findById(id);
    }

    public List<ContentEntity> findAll() {
        return contentRepository.findAll();
    }
}
