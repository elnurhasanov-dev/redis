package com.example.springdatarediscache.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String firstName;
    String lastName;
    Integer age;
    String email;
    String phone;

//    @OneToMany(mappedBy = "customer")
//    private List<AccountEntity> account;
}
