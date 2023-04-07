package org.kamenchuk.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

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
    @javax.persistence.Id
    @Id
    @Column(name = "id", nullable = false,unique = true)
    @GeneratedValue(generator = "cars_id_seq")
    @GenericGenerator(name = "cars_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(name = "car_number",nullable = false,unique = true)
    private String carNumber;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "limitations")
    private String limitations;

    @Column(name = "id_image")
    private Integer idImage;

    @ManyToOne
    @JoinColumn(name = "id_model",referencedColumnName = "id")
    private Model model;

}
