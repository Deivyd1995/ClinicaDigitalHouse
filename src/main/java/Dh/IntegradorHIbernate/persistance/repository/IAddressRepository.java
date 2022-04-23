package Dh.IntegradorHIbernate.persistance.repository;

import Dh.IntegradorHIbernate.persistance.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
}
