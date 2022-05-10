package com.emplify.ema.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
public class Employer {
    @Id
    @GeneratedValue
    private Long id;
    private String businessName;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "employer", fetch = FetchType.LAZY)
    private List<Employee> employees;


}
