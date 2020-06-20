package com.latam.birthday.test.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Entity
public class UsersEntity {

    public UsersEntity(String firstName, String middleName, String lastName, String secondSurname, Date birthDate) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondSurname = secondSurname;
        this.birthDate = birthDate;
    }

    public UsersEntity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;
    private Date birthDate;
    private int age;
    private int leftDays;
    private boolean todayBirthday;
    private String poem;
    private String poet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLeftDays() {
        return leftDays;
    }

    public void setLeftDays(int leftDays) {
        this.leftDays = leftDays;
    }

    public boolean isTodayBirthday() {
        return todayBirthday;
    }

    public void setTodayBirthday(boolean todayBirthday) {
        this.todayBirthday = todayBirthday;
    }

    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    public String getPoet() {
        return poet;
    }

    public void setPoet(String poet) {
        this.poet = poet;
    }

    /**
     *
     *      return hashmap con public data
     *
     * @return Hashmap with public data
     */
    public HashMap<String, Object> getPublicData() {
        HashMap<String, Object> userPublic = new LinkedHashMap<>();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        userPublic.put("firstName", this.firstName);
        userPublic.put("lastName", this.lastName);
        userPublic.put("birthDate", dateFormat.format(this.birthDate));
        userPublic.put("age", this.age);
        userPublic.put("leftDays", this.leftDays);
        userPublic.put("todayBirthday", this.todayBirthday);
        userPublic.put("poem", this.poem);
        userPublic.put("poet", this.poet);

        return userPublic;
    }
}
