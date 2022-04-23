package Dh.IntegradorHIbernate.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class Patient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotEmpty(message = "El nombre no puede estar vacio")
      //  @NonNull
        @Size(min = 2, max = 255, message = "Error: El nombre tiene que tenet entre 2 y 255 caracteres")
        private String name;
        @NotEmpty(message = "El apellido no puede estar vacio")
       // @NonNull
        @Size(min = 2, max = 255, message = "Error: El apellido tiene que tener entre 2 y 255 caracteres")
        private String lastName;
       // @NonNull
        private Integer dni;
       // @NonNull
        private LocalDate admissionDate;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name ="id_address", referencedColumnName = "id")
        private Address address; //= new Address();
        @OneToMany(mappedBy = "patient")
        @JsonIgnore
        private Set<Turn> turns;

        public Patient() {}

}
