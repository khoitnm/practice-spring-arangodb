package org.tnmk.practicespringarangodb.pro02auditablefields.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tnmk.practicespringarangodb.pro02auditablefields.sample.entity.ContentEntity;

public interface ContentRepository extends MongoRepository<ContentEntity, String> {
}
