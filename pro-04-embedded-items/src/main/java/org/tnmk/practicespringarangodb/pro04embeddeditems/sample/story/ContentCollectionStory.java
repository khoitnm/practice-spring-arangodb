package org.tnmk.practicespringarangodb.pro04embeddeditems.sample.story;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.Content;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.ContentCollection;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.repository.ContentCollectionExtendRepository;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.repository.ContentCollectionRepository;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.repository.ContentRepository;

@Service
public class ContentCollectionStory {
    @Autowired
    private ContentCollectionRepository contentCollectionRepository;

    @Autowired
    private ContentCollectionExtendRepository contentCollectionExtendRepository;


    public ContentCollection create(ContentCollection contentCollection) {
        return contentCollectionRepository.save(contentCollection);
    }

    public Optional<ContentCollection> findById(String id) {
        return contentCollectionRepository.findById(id);
    }

    public List<ContentCollection> findAll() {
        return contentCollectionRepository.findAll();
    }

    public void addContentToCollection(String collectionId, Content content){
        contentCollectionExtendRepository.addContentToColllection(collectionId, content);
    }

    public void addUniqueContentToCollection(String collectionId, Content content){
        contentCollectionExtendRepository.addUniqueContentToColllection(collectionId, content);
    }
}
