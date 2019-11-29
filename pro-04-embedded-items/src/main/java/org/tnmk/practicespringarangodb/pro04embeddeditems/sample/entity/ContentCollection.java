package org.tnmk.practicespringarangodb.pro04embeddeditems.sample.entity;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.tnmk.practicespringarangodb.pro04embeddeditems.common.audit.entity.BaseMongoEntity;

@Document("ContentCollection")
public class ContentCollection extends BaseMongoEntity {

    @Field("name")
    private String name;

    @Field("contentItems")
    private List<Content> contentItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Content> getContentItems() {
        return contentItems;
    }

    public void setContentItems(List<Content> contentItems) {
        this.contentItems = contentItems;
    }
}
