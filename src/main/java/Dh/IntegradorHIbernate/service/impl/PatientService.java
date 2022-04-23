package Dh.IntegradorHIbernate.service.impl;


import Dh.IntegradorHIbernate.exceptions.ResourceNotFoundException;
import Dh.IntegradorHIbernate.persistance.entity.Address;
import Dh.IntegradorHIbernate.persistance.entity.Patient;
import Dh.IntegradorHIbernate.persistance.entity.dto.AddressDto;
import Dh.IntegradorHIbernate.persistance.entity.dto.PatientDto;
import Dh.IntegradorHIbernate.persistance.repository.IPatientRepository;
import Dh.IntegradorHIbernate.service.IAddressService;
import Dh.IntegradorHIbernate.service.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private IPatientRepository patientRepository;
    @Autowired
    private IAddressService iAddressService;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public PatientDto register(PatientDto patientDto) {
        Patient patient = mapper.convertValue(patientDto, Patient.class);
        patientRepository.save(patient);
        return mapper.convertValue(patient, PatientDto.class);
    }

    @Override
    public PatientDto findById(Long id) throws ResourceNotFoundException {
        PatientDto patientDto = null;
        Optional<Patient> patient = patientRepository.findById(id);
        if (!patient.isPresent()) {
            throw new ResourceNotFoundException("No existe un paciente con ID " + id);
        }
        return patientDto = mapper.convertValue(patient, PatientDto.class);
    }

    @Override
    public void delete(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.deleteById(id);
        }
    }

    @Override
    public List<PatientDto> findAll() {
        PatientDto patientDto = null;
        List<PatientDto> patientDtoList = new ArrayList<>();
        List<Patient> patientList = patientRepository.findAll();
        for (Patient patient : patientList) {
            patientDto = mapper.convertValue(patient, PatientDto.class);
            patientDtoList.add(patientDto);
        }
        return patientDtoList;
    }

    @Override
    public PatientDto update(PatientDto patientDto) {
        Optional<Patient> optionalPatient = null;
        optionalPatient = mapper.convertValue(patientDto, Optional.class);
        if (optionalPatient.isPresent()) {
            Patient patient1 = mapper.convertValue(optionalPatient, Patient.class);
            Address address = patient1.getAddress();
            AddressDto addressDto = mapper.convertValue(address, AddressDto.class);
            iAddressService.update(addressDto);
            return mapper.convertValue(patientRepository.save(patient1), PatientDto.class);
        }
        return null;
    }


}
