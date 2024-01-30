package com.forAll.sms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
@Entity
@Data
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;


    public Privilege() {

    }
}
