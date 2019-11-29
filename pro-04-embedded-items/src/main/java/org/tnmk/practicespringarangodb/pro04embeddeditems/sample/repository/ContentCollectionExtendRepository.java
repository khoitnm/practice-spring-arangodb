package org.tnmk.practicespringarangodb.pro04embeddeditems.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity.Content;

@Repository
public class ContentCollectionExtendRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addContentToColllection(String collectionId, Content content){
        Update update = new Update();
        update.push("contentItems", content);
        Criteria criteria = Criteria.where("_id").is(collectionId);
        mongoTemplate.updateFirst(Query.query(criteria), update, "ContentCollection");
    }

    /**
     * This method will be a little bit slower because it also check unique Content.
     * @param collectionId
     * @param content
     */
    public void addUniqueContentToColllection(String collectionId, Content content){
        Update update = new Update();
        update.addToSet("contentItems", content);
        Criteria criteria = Criteria.where("_id").is(collectionId);
        mongoTemplate.updateFirst(Query.query(criteria), update, "ContentCollection");
    }
}
