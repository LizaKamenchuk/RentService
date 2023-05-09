package org.kamenchuk.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    private int id;
    private String role;
    @Override
    public String getAuthority() {
        return role;
    }
}
