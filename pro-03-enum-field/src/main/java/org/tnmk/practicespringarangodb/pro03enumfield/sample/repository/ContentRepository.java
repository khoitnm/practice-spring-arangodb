package org.tnmk.practicespringarangodb.pro03enumfield.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tnmk.practicespringarangodb.pro03enumfield.sample.entity.ContentEntity;

public interface ContentRepository extends MongoRepository<ContentEntity, String> {
}
