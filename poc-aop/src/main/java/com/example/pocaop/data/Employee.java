package com.example.pocaop.data;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee implements Serializable {
    private long id;
    private String forename;
    private String surname;
    private String email;
}
