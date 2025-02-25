package com.example.springdatarediscache.modlel.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CustomerRequestDto {
    String firstName;
    String lastName;
    Integer age;
    String email;
    String phone;
}
