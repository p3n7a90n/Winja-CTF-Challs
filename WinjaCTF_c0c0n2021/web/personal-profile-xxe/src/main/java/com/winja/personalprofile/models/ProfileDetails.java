package com.winja.personalprofile.models;

/*
<ProfileDetails>
<name>Placeholder</name>
<age>21</age>
<experience>1</experience>
<country>IN</country>
<location>localhost</location>
<email>test@gmail.com</email>
<phone>12345</phone>
<freelance>Available</freelance>
</ProfileDetails>
*/

public class ProfileDetails {

    private String name;
    private int age;
    private int exp;
    private String country;
    private String location;
    private String email;
    private String phone;
    private String freelance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFreelance() {
        return freelance;
    }

    public void setFreelance(String freelance) {
        this.freelance = freelance;
    }

}
