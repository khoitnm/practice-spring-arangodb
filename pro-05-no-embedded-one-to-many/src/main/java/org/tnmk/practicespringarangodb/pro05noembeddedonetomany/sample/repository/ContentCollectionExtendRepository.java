package org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.entity.Content;

@Repository
public class ContentCollectionExtendRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * This method will partial update the collectionId field.
     * @param collectionId
     * @param contentId
     */
    public void moveContentToCollection(String collectionId, String contentId) {
        Update update = new Update();
        update.set("collection_id", collectionId);
        Criteria criteria = Criteria.where("_id").is(contentId);
        mongoTemplate.updateFirst(Query.query(criteria), update, "Content");
    }
}
