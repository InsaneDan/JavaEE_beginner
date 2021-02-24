package ru.geekbrains.persist;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class User {

    private Long id;

    @NotEmpty(message = "Поле не может быть пустым") // валидация в предметной области // validation by contract
    private String firstName;

    @NotEmpty(message = "Поле не может быть пустым")
    private String lastName;

    // валидатор RegExp pattern с тэгом <f:validateRegex pattern="..."> в user_form.xhtml закомментирован
    @NotEmpty(message = "Поле не может быть пустым")
    @Email(message = "Неверный формат адреса электронной почты: ${validatedValue}",
            regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")
    private String email;

    @NotEmpty(message = "Поле не может быть пустым")
    private String login;

    @NotEmpty(message = "Поле не может быть пустым")
    @Pattern(message = "Пароль должен обязательно содержать цифры, строчные и заглавные буквы.",
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^\\w\\s]).{6,}")
    private String password;

    public User() {}

    public User(Long id, String firstName, String lastName, String email, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
