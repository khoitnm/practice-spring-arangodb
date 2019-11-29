package org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.story;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.entity.Content;
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.entity.ContentCollection;
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.repository.ContentCollectionExtendRepository;
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.repository.ContentCollectionRepository;
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.repository.ContentRepository;

@Service
public class ContentCollectionStory {
    @Autowired
    private ContentCollectionRepository contentCollectionRepository;

    @Autowired
    private ContentCollectionExtendRepository contentCollectionExtendRepository;

    @Autowired
    private ContentRepository contentRepository;

    public ContentCollection create(ContentCollection contentCollection) {
        return contentCollectionRepository.save(contentCollection);
    }

    public Optional<ContentCollection> findById(String id) {
        return contentCollectionRepository.findById(id);
    }

    public List<ContentCollection> findAll() {
        return contentCollectionRepository.findAll();
    }

    public void putContentIntoCollection(String collectionId, String contentId) {
        contentCollectionExtendRepository.moveContentToCollection(collectionId, contentId);
    }

    public Optional<ContentCollection> findCollectionIncludingContentItems(String collectionId) {
        Optional<ContentCollection> contentCollectionOptional = findById(collectionId);
        if (contentCollectionOptional.isPresent()) {
            List<Content> ocntentItems = contentRepository.findByCollectionId(collectionId);
            contentCollectionOptional.get().setContentItems(ocntentItems);
        }
        return contentCollectionOptional;
    }
}
