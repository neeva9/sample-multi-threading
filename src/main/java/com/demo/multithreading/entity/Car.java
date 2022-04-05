package com.demo.multithreading.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CAR_MAKE")
    private String make;

    @Column(name = "CAR_MODEL")
    private String model;

    @Column(name = "CAR_YEAR")
    private String year;
}
