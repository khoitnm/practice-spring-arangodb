# Introduction
In this example, we will create a simple sample relationship one-to-many (a City has many Persons)
, and there's no additional properties of that relationship, hence we don't need any `Edge` collection.

# Design Note
## a User live in a City
You have a collection called users. Users live in homeTown and a homeTown is identified by its primary key. In principle you can embedded the homeTown document into the users document and be happy with it.
Now assume, that you have additional information about the homeTown, like the number of people living in it. It would be impractical to change each and every user document if this numbers changes. 
Therefore it is NOT a good idea to hold the homeTown information inside the User