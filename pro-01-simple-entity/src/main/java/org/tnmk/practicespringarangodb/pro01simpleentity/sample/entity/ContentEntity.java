package org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity;


import java.math.BigInteger;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//Collection name
//If you want to reuse one Entity class for different collections based on the Repositories' names, you can use this solution: https://stackoverflow.com/questions/12274019/how-to-configure-mongodb-collection-name-for-a-class-in-spring-data
//@Document("Content")
public class ContentEntity {
    /**
     * ObjectId vs Uuid: https://stackoverflow.com/questions/28895067/using-uuids-instead-of-objectids-in-mongodb
     * When the type is String or BigInteger, it will be converted and stored as ObjectId in MongoDB: https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb-getting-started
     */
    @Id
    private String id;

    /**
     * Index column: https://www.baeldung.com/spring-data-mongodb-index-annotations-converter
     */
    @Indexed
    private String name;

    @Field("content_size")//custom field name
    private BigInteger contentSize;

    private Instant createDateTime;

    @Override
    public String toString() {
        return "ContentEntity{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", contentSize=" + contentSize +
            ", createDateTime=" + createDateTime +
            '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Instant createDateTime) {
        this.createDateTime = createDateTime;
    }

    public BigInteger getContentSize() {
        return contentSize;
    }

    public void setContentSize(BigInteger contentSize) {
        this.contentSize = contentSize;
    }
}
