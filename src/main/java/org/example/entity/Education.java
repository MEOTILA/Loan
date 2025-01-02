package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.entity.enumss.EducationalLevel;
import org.example.entity.enumss.universityType;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Education extends BaseEntity {
    private LocalDate yearStart;

    private String universityName;

    @Enumerated(EnumType.STRING)
    private EducationalLevel educationLevel;

    @Enumerated(EnumType.STRING)
    private universityType uniType;

    @OneToMany(mappedBy = "education")
    List<StudentUni> students;



}
