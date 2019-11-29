package org.tnmk.practicespringarangodb.pro04embeddeditems.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.Content;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.ContentCollection;

public interface ContentCollectionRepository extends MongoRepository<ContentCollection, String> {
}
