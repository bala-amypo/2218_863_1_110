package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Resource {
    @Id
    @GeneratedValue
    private Long id;
}
