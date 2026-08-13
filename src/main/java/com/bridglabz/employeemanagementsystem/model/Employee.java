package com.bridglabz.employeemanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="employee")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,unique = true,length = 150)
    private String email;

    @Column(nullable = false,length = 100)
    private String department;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal salary;

    @Column(length = 20)
    private String phone;
}
