package org.tnmk.practicespringarangodb.pro02auditablefields.sample.entity;


import java.math.BigInteger;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.tnmk.practicespringarangodb.pro02auditablefields.common.audit.entity.BaseMongoEntity;

//Collection name
//If you want to reuse one Entity class for different collections based on the Repositories' names, you can use this solution: https://stackoverflow.com/questions/12274019/how-to-configure-mongodb-collection-name-for-a-class-in-spring-data
@Document("Content")
public class ContentEntity extends BaseMongoEntity {

    private String name;

    @Field("content_size")//custom field name
    private BigInteger contentSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getContentSize() {
        return contentSize;
    }

    public void setContentSize(BigInteger contentSize) {
        this.contentSize = contentSize;
    }
}
