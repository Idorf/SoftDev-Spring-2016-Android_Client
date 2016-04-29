package activity;

/**
 * Created by Idorf on 17/04/2016.
 */

class Person {

    String name;
    String age;
    int photoId;

    Person(String name, String age, int photoId) {
        System.out.println(name);
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }
}