package org.tnmk.practicespringarangodb.pro01simpleentity.sample.entity;


import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Ref;
import java.time.Instant;
import org.springframework.data.annotation.Id;


@Document("person")
public class Person {

    @Id
    private String id;

    @Field("fullName")
    private String fullName;

    @Field("dateOfBirth")
    private Instant dateOfBirth;

    /**
     * The city which this person is living in (but he could be born in another city)
     */
    //Note: if you use lazy = false, it will NOT do the JOIN query, it will do n+1 query instead. (I see it in the debug log of ArangoDB)
    //So eager loading is a very bad design.
    @Ref(lazy = true)
    private City livingCity;

    /**
     * The city this person was born in.
     */
    @Ref(lazy = true)
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
