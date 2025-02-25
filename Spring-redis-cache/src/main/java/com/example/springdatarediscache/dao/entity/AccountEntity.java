package com.example.springdatarediscache.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String firstName;
    String lastName;
    Integer age;
    String email;
    String phone;

//    @ManyToOne(fetch = EAGER)
//    @JoinColumn(name = "customer_id")
//    private CustomerEntity customer;
}
