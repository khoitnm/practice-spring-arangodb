# Introduction
In this example, we will create a simple sample relationship one-to-many (a City has many Persons)
, and there's no additional properties of that relationship, hence we don't need any `Edge` collection.

# Design Note
## a User live in a City
You have a collection called users. Users live in homeTown and a homeTown is identified by its primary key. In principle you can embedded the homeTown document into the users document and be happy with it.
Now assume, that you have additional information about the homeTown, like the number of people living in it. It would be impractical to change each and every user document if this numbers changes. 
Therefore it is NOT a good idea to hold the homeTown information inside the User

# Some Notes
In arango-spring-data:3.2.3:

## 1. Lazy loading
If you use lazy = false (you can try in `Person` class), it will NOT do the JOIN query, it will do n+1 query instead. (I see it in the debug log of ArangoDB)
So eager loading is a very bad design.

## 2. Join query with merged result.
The custom query for Joining *does NOT really work* when we want to return a merged object.
```
    /**
     * When running the query directly in ArangoDB client, it works.
     * Running in Java app, it was also able to return JSON response in the debug log message of ArangoDB.
     * However, it cannot convert that JSON data into Java class.
     * @deprecated I still NOT able to find a solution for this.
     * @return
     */
    @Deprecated
    @Query(" FOR p IN person " +
            " FOR ht IN city " +
            " FOR lc IN city " +
            " FILTER p.homeTown == ht._id AND p.livingCity == lc._id" +
            " RETURN merge (p, {homeTown: ht}, {livingCity: lc})")
    Iterable<Person> findAllDetailPersons();
```

In `Person` entity:
```
public class Person {
    //...
    @Ref
    private City homeTown;
    //...
} 
```
    
    
Error:
```
org.springframework.data.mapping.MappingException: A reference must be of type String, but got VPack type OBJECT! 
```

And when that error happened, it did NOT throw any exception!!! It just write exception into the log and return a null result!!!

Note: however, when I remove @Ref, I can query it normally (you can see int `PersonView`.