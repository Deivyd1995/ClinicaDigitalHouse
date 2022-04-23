package Dh.IntegradorHIbernate.persistance.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne//(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @ManyToOne //(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;
    @NonNull
    private LocalDate dateTurn;
    private Time time;

    public Turn() {}
}
