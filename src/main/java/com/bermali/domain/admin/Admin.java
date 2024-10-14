package com.bermali.domain.admin;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Admin implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String login;
    private String password;
    private Role role;

    // CONSTRUCTOR  --------------------------------------------------------

    public Admin(){
    }

    public Admin(String name, String email, String password) {
        this.name = name;
        this.login = email;
        this.password = password;
        this.role = Role.USER;
    }

    // GETTERS AND SETTERS -------------------------------------------------

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String email) {
        this.login = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // IMPLEMENT USERDETAILS -------------------------------------------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }
}
