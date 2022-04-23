package Dh.IntegradorHIbernate.persistance.entity.dto;


import Dh.IntegradorHIbernate.persistance.entity.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDto {

    private Long id;
    private String name;
    private String lastName;
    private Integer dni;
    private LocalDate admissionDate;
    private Address address; //= new Address();

    public PatientDto() {}

}
