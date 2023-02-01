package org.kamenchuk.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    private Integer id;
    private String carNumber;
    private Integer price;
    private String limitations;
    private Integer idImage;
    private Model model;
}
