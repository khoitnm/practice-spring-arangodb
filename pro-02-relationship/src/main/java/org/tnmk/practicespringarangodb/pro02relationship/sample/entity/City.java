package org.tnmk.practicespringarangodb.pro02relationship.sample.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Document("city")
public class City {
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("lat")
    private BigDecimal latitude;

    @Field("long")
    private BigDecimal longitude;

    /**
     * This field is used only for convenient retrieving data.
     * It's should not be used for updating data.
     */
    @Transient
    private List<Person> persons;

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
