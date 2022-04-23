package Dh.IntegradorHIbernate.persistance.repository;

import Dh.IntegradorHIbernate.persistance.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITurnRepository extends JpaRepository<Turn, Long> {

    @Query(value = "SELECT * FROM Turn  WHERE patient_id = ?1", nativeQuery = true)
    List<Turn> viewAllTurnsForPatient(Long patient_id);
}
