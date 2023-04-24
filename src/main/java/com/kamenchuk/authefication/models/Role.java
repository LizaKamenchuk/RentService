package com.kamenchuk.authefication.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id",nullable = false,unique = true)
    @GeneratedValue(generator = "roles_id_seq")
    @GenericGenerator(name = "roles_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private int id;

    @Column(name = "role",nullable = false,unique = true)
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
