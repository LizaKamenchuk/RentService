package org.kamenchuk.models;

import lombok.*;

import java.io.StringReader;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int id;
    private String role;
}
