package org.kamenchuk.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int id;

    @Column(name = "role",nullable = false)
    private String role;
}
