package com.kamenchuk.authefication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id",nullable = false,unique = true)
    @GeneratedValue(generator = "users_id_seq")
    @GenericGenerator(name = "users_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Long id;
    @Column(name = "login", length = 50, nullable = false, unique = true)
    private String login;
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;
    @OneToOne
    @JoinColumn(name = "id_role",nullable = false)
    private Role role;

    @Column(name="id_extra_users_data")
    private Long id_extra_user_data;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Arrays.asList(role);
    }

    @Override
    public String getUsername() {
        return getLogin();
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
