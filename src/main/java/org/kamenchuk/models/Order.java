package org.kamenchuk.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder//read
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate",nullable = false)
    private LocalDate finishDate;

    @Column(name = "status",nullable = false)
    private Boolean status;

    @Column(name = "adminsLogin")
    private String adminsLogin;

    @Column(name = "refuseReason",nullable = false)
    private String refuseReason;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "idUser",nullable = false)
    private User client;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "idCar",nullable = false)
    private Car car;
}
