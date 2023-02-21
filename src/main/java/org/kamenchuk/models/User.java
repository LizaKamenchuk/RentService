package org.kamenchuk.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login", length = 50, nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "idRole",nullable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idExtraUsersData")
    private ExtraUsersData extraUsersData;

    //TODO fetch убрать , когда перепишу getAll c dto
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Order> order;

}

