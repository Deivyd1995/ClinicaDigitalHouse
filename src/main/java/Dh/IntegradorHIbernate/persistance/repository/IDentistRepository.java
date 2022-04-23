package Dh.IntegradorHIbernate.persistance.repository;

import Dh.IntegradorHIbernate.persistance.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface IDentistRepository extends JpaRepository<Dentist, Long> {

    @Query("SELECT d FROM Dentist d WHERE d.name = ?1")
         List<Dentist>  buscarDentist(String name);


}
