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
@Table(name = "extra_data_car")
public class ExtraDataCar {

    @Id
    @Column(name = "id", nullable = false,unique = true)
    @GeneratedValue(generator = "extra_data_car_id_seq")
    @GenericGenerator(name = "extra_data_car_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(name = "manufacture_year",nullable = false)
    private Integer manufacture_year;

    @Column(name = "fuel_consumption", nullable = false)
    private Double fuel_consumption;

    @Column(name = "limitations")
    private String limitations;

    @OneToOne
    @JoinColumn(name = "transmission_id", referencedColumnName = "id")
    private Transmission transmission;

    @OneToOne
    @JoinColumn(name = "fuel_id", referencedColumnName = "id")
    private Fuel fuel;

    @OneToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private CarClass carClass;
}
