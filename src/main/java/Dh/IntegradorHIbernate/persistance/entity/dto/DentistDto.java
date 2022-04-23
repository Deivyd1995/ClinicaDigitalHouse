package Dh.IntegradorHIbernate.persistance.entity.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DentistDto {

    private Long id;

    private int numeroMatricula;

    private String name;

    private String lastName;

    public DentistDto(){}
}
