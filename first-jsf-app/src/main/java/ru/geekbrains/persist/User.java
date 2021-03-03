package ru.geekbrains.persist;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "findAllUsers", query = "FROM User"),
        @NamedQuery(name = "countAllUsers", query = "SELECT COUNT(*) FROM User"),
        @NamedQuery(name = "deleteUserById", query = "DELETE FROM User u WHERE u.id = :id")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 2)
    @Column(nullable = false)
    private String firstName;

    @Length(min = 2)
    @Column(nullable = false)
    private String lastName;

    @Email(message = "Неверный формат адреса электронной почты: ${validatedValue}",
            regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")
    @Column(nullable = false)
    private String email;

    @Length(min = 2)
    @Column(nullable = false)
    private String login;

    @Pattern(message = "Пароль должен обязательно содержать цифры, строчные и заглавные буквы.",
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^\\w\\s]).{6,}")
    @Column(nullable = false)
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
