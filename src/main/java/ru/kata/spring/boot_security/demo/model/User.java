package ru.kata.spring.boot_security.demo.model;

//import jakarta.persistence.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String userName;

    @Column(name = "status")
    private String userStatus;
    @Column(name = "age")
    private int userAge;

    public User() {
    }

    public User(String userName, String userStatus, int userAge) {
        this.userName = userName;
        this.userStatus = userStatus;
        this.userAge = userAge;
    }

    public User(Long id, String userName, String userStatus, int userAge) {
        this.id = id;
        this.userName = userName;
        this.userStatus = userStatus;
        this.userAge = userAge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", userAge=" + userAge +
                '}';
    }
}
