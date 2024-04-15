package com.springJPA.Library.Modal;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequest {
  private  String username;
  private  String password;

}
