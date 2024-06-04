package com.NTApp.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@AllArgsConstructor
@Data
@Table(name = "utilisateur_tb")

public class Utilisateurs implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column
    @NonNull
    @Size(max = 300)
    private String nom;
    @Column
    @NonNull
    @Size(max = 300)
    private String prenom;
    @Column
    @NonNull
    @Size(max = 300)
    @Email
    private String email;
    @Column
    @Size(max = 300)
    private String telephone;
    @Column
    @NonNull
    @Size(max = 300)
    private String motdepasse;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private roles roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return getMotdepasse();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
