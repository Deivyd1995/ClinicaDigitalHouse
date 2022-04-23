package Dh.IntegradorHIbernate.persistance.entity;


import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "La calle no puede estar vacia")
    @NonNull
    @Size(min = 2, max = 255, message = "Error: La calle tiene que tener entre 2 y 255 caracteres")
    private String street;
    @NonNull
    private int number;
    @NotEmpty(message = "La localidad no puede estar vacia")
    @NonNull
    @Size(min = 2, max = 255, message = "Error: La localidad tiene que tener entre 2 y 255 caracteres")
    private String location;
    @NotEmpty(message = "La provincia no puede estar vacia")
    @NonNull
    @Size(min = 2, max = 255, message = "Error: La provincia tiene que tener entre 2 y 255 caracteres")
    private String province;

    public Address() {}

}
