package org.kamenchuk.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "extraUsersData")
public class ExtraUsersData {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "idPassport")
    private String idPassport;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "drivingLicense")
    private String drivingLicense;

    @Column(name = "phone")
    private String phone;

    @Column(name = "registerDate")
    private Date registerDate;
}
