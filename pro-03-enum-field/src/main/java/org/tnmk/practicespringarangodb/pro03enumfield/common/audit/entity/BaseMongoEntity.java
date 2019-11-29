package org.tnmk.practicespringarangodb.pro03enumfield.common.audit.entity;

import java.time.Instant;
import org.springframework.data.annotation.Id;

/**
 * <pre>
 * I named this class `BaseMongoEntity` because for each kind of DB, the id field could use different data types:
 *
 * For MongoDB, we'll use String.
 * For MySQL, it could be Integer or Long.
 * For Cassandra, it could be UUID.
 * For Neo4j, it could be Long.
 * </pre>
 */
public class BaseMongoEntity {
    /**
     * ObjectId vs Uuid: https://stackoverflow.com/questions/28895067/using-uuids-instead-of-objectids-in-mongodb
     * When the type is String or BigInteger, it will be converted and stored as ObjectId in MongoDB: https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb-getting-started
     */
    @Id
    private String id;

    private Instant createdDateTime;
    private Instant updatedDateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Instant getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Instant updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
