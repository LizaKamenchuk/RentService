package org.kamenchuk.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder//read
public class Model {
    private Integer idModel;
    private String model;
    private Integer idMark;
    private String mark;
}
