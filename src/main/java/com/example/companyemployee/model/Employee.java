package com.example.companyemployee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int id;
    private String name;
    private String surname;
    private String email;
    private Company company;
}
