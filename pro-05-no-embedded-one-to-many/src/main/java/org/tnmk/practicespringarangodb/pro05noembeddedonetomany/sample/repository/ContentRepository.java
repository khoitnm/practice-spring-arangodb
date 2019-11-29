package org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.entity.Content;

import java.util.List;

public interface ContentRepository extends MongoRepository<Content, String> {

    List<Content> findByCollectionId(String collectionId);
}
