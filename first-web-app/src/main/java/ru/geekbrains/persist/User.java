package ru.geekbrains.persist;

import java.math.BigDecimal;

public class User {

    private Long id;

    private String first_name;

    private String last_name;

    private String birth_date;

    private String phone_number;

    private String email;

    private String address;

    private String login;

    private String password;


    public User() {
    }

    public User(Long id, String first_name, String last_name, String birth_date, String phone_number, String email, String address, String login, String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
