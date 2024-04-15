package com.springJPA.Library.Modal;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

    private String username;
    private String jwtToken;
}
