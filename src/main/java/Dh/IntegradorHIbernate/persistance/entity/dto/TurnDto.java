package Dh.IntegradorHIbernate.persistance.entity.dto;

import Dh.IntegradorHIbernate.persistance.entity.Dentist;
import Dh.IntegradorHIbernate.persistance.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
public class TurnDto {

    private Long id;

    private Patient patient;

    private Dentist dentist;

    private LocalDate dateTurn;

    private Time time;

    public TurnDto() {
    }
}
