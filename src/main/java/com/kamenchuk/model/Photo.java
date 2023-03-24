package com.kamenchuk.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("images")
public class Photo {
    @Id
    private String id;
    private Integer idCar;
    private File file;
}
