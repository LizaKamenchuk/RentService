package org.kamenchuk.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder//read
public class Order {
    private Long id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Boolean status;
    @EqualsAndHashCode.Exclude
    private Car car;
    @EqualsAndHashCode.Exclude
    private User client;
    private String adminsLogin;
    private String refuseReason;
}
