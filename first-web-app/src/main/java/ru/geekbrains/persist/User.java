package ru.geekbrains.persist;

public class User {

    private Long id;

    private String first_name;

    private String last_name;

    private String email;

    private String login;

    private String password;


    public User() {
    }

    public User(Long id, String first_name, String last_name, String email, String login, String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
