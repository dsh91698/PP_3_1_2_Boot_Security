package ru.kata.spring.boot_security.demo.model;

//import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String userName;

    @Column(name = "status")
    private String userStatus;
    @Column(name = "age")
    private int userAge;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //TODO
    @JoinColumn(name = "user_id")
    private List<Role> roles;

    public User() {
    }

    public User(String userName, String userStatus, int userAge, String login, String password, List<Role> roles) {
        this.userName = userName;
        this.userStatus = userStatus;
        this.userAge = userAge;
        this.login = login;
        this.password = password;
        this.roles = roles;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    } //password

    @Override
    public String getUsername() {
        return login;
    } //login

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", userAge=" + userAge +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
