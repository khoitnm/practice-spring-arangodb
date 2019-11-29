package org.tnmk.practicespringarangodb.pro04embeddeditems.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.Content;

public interface ContentRepository extends MongoRepository<Content, String> {
}
