package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.entity.enumss.City;
import org.example.entity.enumss.LoanType;
import org.example.entity.enumss.Pardakht;

import java.util.List;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Loan extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private LoanType type;

    @Enumerated(EnumType.STRING)
    private Pardakht pardakht;

    @OneToMany(mappedBy = "loan")
    List<StudentUni> students;
}
