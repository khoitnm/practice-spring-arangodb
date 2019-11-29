package org.tnmk.practicespringarangodb.pro01simpleentity.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.ContentEntity;

public interface ContentRepository extends MongoRepository<ContentEntity, String> {
}
