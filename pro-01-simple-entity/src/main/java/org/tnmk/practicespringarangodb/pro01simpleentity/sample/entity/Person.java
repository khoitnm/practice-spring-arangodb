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

    @Ref
    private City homeTown;

    @Ref
    private City workingCity;

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", homeTown=" + homeTown +
                ", workingCity=" + workingCity +
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

    public City getWorkingCity() {
        return workingCity;
    }

    public void setWorkingCity(City workingCity) {
        this.workingCity = workingCity;
    }
}
