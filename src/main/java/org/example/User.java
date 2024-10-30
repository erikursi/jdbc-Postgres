package org.example;

import java.sql.Date;
import java.time.Instant;

public class User {
    private String login;
    private String password;
    private String email;
    private Date date;
    public User (String login, String password, String email, Date date) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = date;
    }
    public User (String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = new Date(System.currentTimeMillis());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                '}';
    }
}
