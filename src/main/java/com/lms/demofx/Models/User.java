package com.lms.demofx.Models;

public class User {

    private String name;
    private int age;
    private String province;

    public User(String name, int age, String province) {
        this.name = name;
        this.age = age;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getProvince() {
        return province;
    }


}
