package org.tnmk.practicespringarangodb.pro05noembeddedonetomany.sample.entity;

import java.util.List;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.common.audit.entity.BaseMongoEntity;

@Document("ContentCollection")
public class ContentCollection extends BaseMongoEntity {

    @Field("name")
    private String name;

    /**
     * This field is used to query only, not for storing.
     * The reason we need this field is trying to reuse DB model for business model.
     * This field actually belongs to business model, not DB model.
     * <p/>
     * However, this is not really a good way to handle this case.
     * When the system becomes more complicate, we should think about using another model class such as `CollectionIncludingContentItems`.
     * That would be the simple version of CQRS pattern, which is better than this current code.
     */
    @Transient
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
