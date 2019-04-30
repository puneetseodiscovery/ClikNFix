package com.cliknfix.user.signUp;

public class BeanModelSignUp {

    private String name;
    private String email;
    private String age;
    private String blood_group;
    private String address;
    private String password;

    public BeanModelSignUp(String name, String email, String age, String blood_group, String address, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.blood_group = blood_group;
        this.address = address;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
