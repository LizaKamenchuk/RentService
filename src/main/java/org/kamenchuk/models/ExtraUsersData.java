package org.kamenchuk.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "extra_users_data")
public class ExtraUsersData {
    @Id
    @Column(name = "id",nullable = false,unique = true)
    @GeneratedValue(generator = "extra_users_data_id_seq")
    @GenericGenerator(name = "extra_users_data_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Long id;

    @Column(name = "id_passport",unique = true)
    private String idPassport;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "driving_license",unique = true)
    private String drivingLicense;

    @Column(name = "phone")
    private String phone;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @ToString.Exclude
    @OneToOne(mappedBy = "extraUsersData")
    private User user;

}
