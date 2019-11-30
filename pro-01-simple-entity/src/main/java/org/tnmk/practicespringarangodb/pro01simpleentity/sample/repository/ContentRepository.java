package org.tnmk.practicespringarangodb.pro01simpleentity.sample.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import org.springframework.stereotype.Repository;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity.ContentEntity;

@Repository
public interface ContentRepository extends ArangoRepository<ContentEntity, String> {
}
