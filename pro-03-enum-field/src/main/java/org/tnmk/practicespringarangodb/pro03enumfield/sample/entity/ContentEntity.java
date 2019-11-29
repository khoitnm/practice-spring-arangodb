package org.tnmk.practicespringarangodb.pro03enumfield.sample.entity;


import java.math.BigInteger;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.tnmk.practicespringarangodb.pro03enumfield.common.audit.entity.BaseMongoEntity;

//Collection name
//If you want to reuse one Entity class for different collections based on the Repositories' names, you can use this solution: https://stackoverflow.com/questions/12274019/how-to-configure-mongodb-collection-name-for-a-class-in-spring-data
@Document("Content")
public class ContentEntity extends BaseMongoEntity {

    private String name;
    //When saving, it will be stored as enum.name() (?)
    private ContentType contentType;

    @Field("content_size")//custom field name
    private BigInteger contentSize;

    @Override
    public String toString() {

        return "ContentEntity{" +
            "name='" + name + '\'' +
            ", contentType=" + contentType +
            ", contentSize=" + contentSize +
            '}';
    }

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

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
