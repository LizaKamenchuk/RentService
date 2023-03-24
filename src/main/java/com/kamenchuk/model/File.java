package com.kamenchuk.model;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
@Builder
public class File {
    private String name;
    private byte[]bytes;
}
