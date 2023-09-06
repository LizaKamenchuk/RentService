package org.kamenchuk.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fuel")
public class Fuel {
    @Id
    @Column(name = "id", nullable = false,unique = true)
    @GeneratedValue(generator = "fuel_id_seq")
    @GenericGenerator(name = "fuel_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(name = "fuel_type",nullable = false,unique = true)
    private String fuelType;
}
