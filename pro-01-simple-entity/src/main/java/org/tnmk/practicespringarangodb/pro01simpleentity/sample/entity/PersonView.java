package org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity;


import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Ref;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;


/**
 * The only difference with {@link Person} is this class doesn't use {@link Ref} annotation on {@link PersonView#homeTown} and {@link PersonView#livingCity}.
 * Note: both {@link PersonView} and {@link Person} get data from the same collection.
 */
@Document("person")
public class PersonView {

    @Id
    private String id;

    @Field("fullName")
    private String fullName;

    @Field("dateOfBirth")
    private Instant dateOfBirth;

    /**
     * There's no Ref here, this field is used for querying only.
     */
    @Transient
    private City livingCity;

    /**
     * There's no Ref here, this field is used for querying only.
     */
    @Transient
    private City homeTown;

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", livingCity=" + livingCity +
                ", homeTown=" + homeTown +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public City getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(City homeTown) {
        this.homeTown = homeTown;
    }

    public City getLivingCity() {
        return livingCity;
    }

    public void setLivingCity(City livingCity) {
        this.livingCity = livingCity;
    }
}
