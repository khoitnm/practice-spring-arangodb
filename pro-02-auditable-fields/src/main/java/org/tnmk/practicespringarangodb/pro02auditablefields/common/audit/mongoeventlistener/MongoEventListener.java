package org.tnmk.practicespringarangodb.pro02auditablefields.common.audit.mongoeventlistener;

import java.time.Instant;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;
import org.tnmk.practicespringarangodb.pro02auditablefields.common.audit.entity.BaseMongoEntity;
import org.tnmk.practicespringarangodb.pro02auditablefields.common.audit.util.reflection.ReflectionTraverseUtils;

/**
 * @author kevin.tran
 * Guideline:
 * http://www.baeldung.com/cascading-with-dbref-and-lifecycle-events-in-spring-data-mongodb
 * https://www.baeldung.com/spring-boot-mongodb-auto-generated-field
 */
@Component
public class MongoEventListener extends AbstractMongoEventListener<Object> {

    /**
     * @param event
     * @explanation use {@link #onBeforeConvert(BeforeConvertEvent)}, don't use {@link #onBeforeSave(BeforeSaveEvent)} because all the setter into the entity will be useless at this phrases (I have not investigate to understand why yet)
     */
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        //We have to use traverseEntity because we want to update the auditable fields of the children entities, too.
        ReflectionTraverseUtils.traverseEntity(
                source
                , (ReflectionTraverseUtils.ActionStatus actionStatus) -> {
                    Object objectValue = actionStatus.getObjectValue();
                    if (objectValue == null) return false;
                    if (objectValue instanceof BaseMongoEntity) {
                        BaseMongoEntity baseMongoEntity = (BaseMongoEntity) objectValue;
                        if (baseMongoEntity.getCreatedDateTime() == null) {
                            baseMongoEntity.setCreatedDateTime(Instant.now());
                        } else {
                            baseMongoEntity.setUpdatedDateTime(Instant.now());
                        }
                        //TODO We don't need to handle Id field anymore because Spring framework will automatically handle it for us.
                        // However, I have not checked whether Spring framework will handle children entities or not.
//                        if (StringUtils.isEmpty(baseMongoEntity.getId())) {
//                            ObjectId id = new ObjectId(new Date());
//                            baseMongoEntity.setId(id.toString());
//                        }
                    }
                    return true;
                }
        );
    }

}
