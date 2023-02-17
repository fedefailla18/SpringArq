package com.helper.work.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "email", unique = true)
    private String email;

    private String firstName;

    private String lastName;

    @Column(name = "phone")
    private String phone;

    @OneToOne
    @JoinColumn(name = "address")
    private Address address;

//    @Column(name = "role")
//    private String role;

    @Column(name = "created_at")
    private String createdAt;
}
