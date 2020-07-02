package com.insight.crescendo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
}
