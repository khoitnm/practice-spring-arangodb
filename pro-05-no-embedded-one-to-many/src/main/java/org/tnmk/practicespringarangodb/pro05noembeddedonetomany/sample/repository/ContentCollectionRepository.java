package org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.entity.Content;
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.entity.ContentCollection;

public interface ContentCollectionRepository extends MongoRepository<ContentCollection, String> {
}
