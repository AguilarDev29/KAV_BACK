package com.example.KAV.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


}
