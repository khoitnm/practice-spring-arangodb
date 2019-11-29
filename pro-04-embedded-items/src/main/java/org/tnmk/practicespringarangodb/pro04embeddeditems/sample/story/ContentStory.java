package org.tnmk.practicespringarangodb.pro04embeddeditems.sample.story;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.Content;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.repository.ContentRepository;

@Service
public class ContentStory {
    private final ContentRepository contentRepository;

    @Autowired
    public ContentStory(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content create(Content content) {
        return contentRepository.save(content);
    }
    public Content update(Content content) {
        return contentRepository.save(content);
    }
    public Optional<Content> findById(String id){
        return contentRepository.findById(id);
    }

    public List<Content> findAll() {
        return contentRepository.findAll();
    }
}
