package Dh.IntegradorHIbernate.controller;


import Dh.IntegradorHIbernate.exceptions.ResourceNotFoundException;
import Dh.IntegradorHIbernate.persistance.entity.dto.PatientDto;
import Dh.IntegradorHIbernate.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private IPatientService iPatientService;

    @PostMapping("/save")
    public ResponseEntity<PatientDto> save(@Valid @RequestBody PatientDto patient) {
        PatientDto patientDto = iPatientService.register(patient);
        return new ResponseEntity<PatientDto>(patientDto, HttpStatus.OK);
    }

    @GetMapping("/id/{patientId}")
    public ResponseEntity<?> search(@PathVariable Long patientId) throws ResourceNotFoundException {
        PatientDto patientDto = iPatientService.findById(patientId);
        return new ResponseEntity<PatientDto>(patientDto, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<?> delete(@PathVariable Long patientId){
        iPatientService.delete(patientId);
        return ResponseEntity.ok("Patient delete complete");
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<PatientDto>> getPatients() {
        List<PatientDto> patientDtoList = iPatientService.findAll();
        return new ResponseEntity<>(patientDtoList, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody PatientDto patient) {
        PatientDto patientDto = iPatientService.update(patient);
        return new ResponseEntity<PatientDto>(patientDto, HttpStatus.OK);
    }

}
