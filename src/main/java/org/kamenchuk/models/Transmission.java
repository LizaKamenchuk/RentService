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
@Table(name = "transmission")
public class Transmission {

    @Id
    @Column(name = "id", nullable = false,unique = true)
    @GeneratedValue(generator = "transmission_id_seq")
    @GenericGenerator(name = "transmission_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(name="transmission_type",nullable = false, unique = true)
    private String transmissionType;
}
