package com.example.springdatarediscache.modlel.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CustomerResponseDto {

    Long id;
    String firstName;
    String lastName;
    Integer age;
    String email;
    String phone;
}
