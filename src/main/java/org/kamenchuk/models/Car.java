package org.kamenchuk.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "carNumber",nullable = false)
    private String carNumber;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "limitations")
    private String limitations;

    @Column(name = "idImage")
    private Integer idImage;

//    @ManyToOne
//    @JoinColumn(name = "idModel",referencedColumnName = "id")
//    private Model model;
}
