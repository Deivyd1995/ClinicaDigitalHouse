package Dh.IntegradorHIbernate.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // @NonNull
    private int numeroMatricula;
    @NotEmpty(message = "El nombre no puede estar vacio")
   // @NonNull
    @Size(min = 2, max = 255, message = "Error: El nombre tiene que tenet entre 2 y 255 caracteres")
    private String name;
    @NotEmpty(message = "El apellido no puede estar vacio")
   // @NonNull
    @Size(min = 2, max = 255, message = "Error: El apellido tiene que tener entre 2 y 255 caracteres")
    private String lastName;
    @OneToMany(mappedBy = "dentist")
    @JsonIgnore
    private Set<Turn> turns;


    public Dentist(){}

}