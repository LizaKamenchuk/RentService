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
@Builder//read
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id", nullable = false,unique = true)
    @GeneratedValue(generator = "orders_id_seq")
    @GenericGenerator(name = "orders_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date",nullable = false)
    private LocalDate finishDate;

    @Column(name = "status",nullable = false)
    private Boolean status;

    @Column(name = "admins_login")
    private String adminsLogin;

    @Column(name = "refuse_reason")
    private String refuseReason;

    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user",nullable = false)
    private User client;

    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_car",nullable = false)
    private Car car;
}
