package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.example.entity.enumss.City;
import org.example.entity.enumss.Gender;
import org.example.entity.enumss.MaritalStatus;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentUni extends BaseEntity {

    String name;


    String lastName;


    String fatherName;


    String motherName;

    @Column(unique = true)
    @Size(min = 10, max = 10)
    String nationalCode;

    @Column(unique = true)
    String certificateNumber;


    LocalDate birthDate;


    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(unique = true)
    String studentNumber;

    @Column(unique = true)
    @Pattern(regexp = "^\\d{10}$")
    String userName;

    @Column(unique = true)
     @Pattern(regexp = " \"^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$\"")
    String password;

    @ManyToOne
    Education education;

    @ManyToOne
    Loan loan;

    @Enumerated(EnumType.STRING)
    private City city;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;


    private boolean isDormitory;


    @Size(min = 16, max = 16)
    private String cardNumber;

}
