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
@Table(name = "carsModels")
public class Model {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idModel;

    @Column(name = "model",nullable = false)
    private String model;

    @ManyToOne
    @JoinColumn(name = "idMark",referencedColumnName = "id")
    private Mark mark;
}
