package Dh.IntegradorHIbernate.persistance.repository;


import Dh.IntegradorHIbernate.persistance.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

}
