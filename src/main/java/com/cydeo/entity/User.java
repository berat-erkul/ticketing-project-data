package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String userName;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    private Role role;
}
