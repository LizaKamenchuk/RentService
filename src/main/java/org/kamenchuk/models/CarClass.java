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
@Table(name = "class")
public class CarClass {
    @Id
    @Column(name = "id", nullable = false,unique = true)
    @GeneratedValue(generator = "class_id_seq")
    @GenericGenerator(name = "class_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(name = "class_type")
    private String classType;

}
