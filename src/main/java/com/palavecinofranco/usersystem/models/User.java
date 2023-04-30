package com.palavecinofranco.usersystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "users")
@Entity
public class User {
    @Getter @Setter @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter @Setter @Column(name = "name")
    private String name;
    @Getter @Setter @Column(name = "lastname")
    private String lastname;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "password")
    private String password;
    @Getter @Setter @Column(name = "cellphone")
    private String cellphone;



}
