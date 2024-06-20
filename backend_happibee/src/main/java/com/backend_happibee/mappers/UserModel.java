/*
package com.backend_happibee.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.springframework.security.core.userdetails.UserDetails;

import com.backend_happibee.infrastructure.authentication.Role;

import lombok.Data;

@Data
@Entity(name="Users")
public class UserModel implements UserDetails {
    
    @Id
    @GeneratedValue
    private String userID;
    private String fullName;
    @Email
    @Column(unique = true)
    private String username;
    private String password;
    @ElementCollection
    private Set<Role> authorities = new HashSet<>();
    private String nif;
    private String morada;

    public UserModel(final String username, final String password){
        this.username = username;
        this.password = password;
    }

    public UserModel(final String username, final String password, final String fullName, final String nif, final String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public void setNif(String nif) {
        if(nif.length() != 9) {
            throw new IllegalArgumentException("NIF must be 9 characters.");
        }
        this.nif = nif;
    }

    public void addAuthority(Role r) {
        authorities.add(r);
    }

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

}
*/
