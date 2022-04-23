package Dh.IntegradorHIbernate.service.impl;

import Dh.IntegradorHIbernate.exceptions.ResourceNotFoundException;
import Dh.IntegradorHIbernate.persistance.entity.Address;
import Dh.IntegradorHIbernate.persistance.entity.dto.PatientDto;
import Dh.IntegradorHIbernate.service.IPatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    private IPatientService iPatientService;


    @Test
   public void testRegisterPatientAndFindAll() throws ResourceNotFoundException {

        Address address = new Address();
        address.setStreet("San Luis");
        address.setNumber(3376);
        address.setLocation("Almagro");
        address.setProvince("CABA");

        PatientDto patientDto= new PatientDto();
        patientDto.setName("Jorge");
        patientDto.setLastName("Lopez");
        patientDto.setDni(38939746);
        patientDto.setAdmissionDate(LocalDate.of(2022,4,1));
        patientDto.setAddress(address);

        iPatientService.register(patientDto);
        List<PatientDto> patientList = iPatientService.findAll();
        assertEquals(true,!patientList.isEmpty());

    }

    @Test
   public void testFindById() throws ResourceNotFoundException {
        PatientDto patientDto = iPatientService.findById(1l);
        System.out.println(patientDto.getName());
        assertEquals(true, patientDto.getName() != iPatientService.findById(1l).getName());
    }


}