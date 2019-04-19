package com.cliknfix.signUp;

public class BeanModelSignUp {

    private String username;
    private String email;
    private String age;
    private String bloodGroup;
    private String address;
    private String password;
    private String role_id;

    public BeanModelSignUp(String username, String email, String age, String bloodGroup, String address, String password,String role_id) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.password = password;
        this.role_id = role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
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

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

}
