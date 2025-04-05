package com.cydeo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false,nullable = false)
    private LocalDate insertDateTime;
    @Column(updatable = false,nullable = false)
    private Long insertUserId;
    @Column(nullable = false)
    private LocalDate lastUpdateDateTime;
    @Column(nullable = false)
    private Long lastUpdateUserId;

    Boolean isDeleted = false;

    @PrePersist
    private void onPrePersist(){
        this.insertDateTime = LocalDate.now();
        this.lastUpdateDateTime = LocalDate.now();
        this.insertUserId= 1L; //we'll change it later !!!!!!
        this.lastUpdateUserId= 1L; //we'll change it later !!!!!!
    }

    @PreUpdate
    private void onPreUpdate(){
        this.lastUpdateDateTime = LocalDate.now();
        this.lastUpdateUserId= 1L; //we'll change it later !!!!!!
    }
}
