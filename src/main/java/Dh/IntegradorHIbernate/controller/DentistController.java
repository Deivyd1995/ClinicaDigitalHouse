package Dh.IntegradorHIbernate.controller;

import Dh.IntegradorHIbernate.exceptions.BadRequestException;
import Dh.IntegradorHIbernate.exceptions.ResourceNotFoundException;
import Dh.IntegradorHIbernate.persistance.entity.dto.DentistDto;
import Dh.IntegradorHIbernate.service.impl.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private DentistService iDentistService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody DentistDto dentist){
        DentistDto dentistDto1 = iDentistService.register(dentist);
        return new ResponseEntity<DentistDto>(dentistDto1, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/id/{dentistId}")
    public ResponseEntity<?> search(@PathVariable Long dentistId)throws ResourceNotFoundException {
        DentistDto dentistDto = iDentistService.findById(dentistId);
        return new ResponseEntity<DentistDto>(dentistDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{dentistId}")
    public ResponseEntity<?> delete(@PathVariable Long dentistId)throws BadRequestException {
        try{
            iDentistService.delete(dentistId);
            return new  ResponseEntity<>("Dentist delete complete", HttpStatus.OK);
        }catch (Exception e){
            throw new BadRequestException("El dentista con id: "+dentistId +", que se desea eliminar no existe. " + e.getMessage());
        }

    }

    @GetMapping("/viewAll")
    public List<DentistDto> getDentists(){
        return iDentistService.findAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody DentistDto dentistDto) throws BadRequestException{
        if (iDentistService.update(dentistDto)==null){
            throw new BadRequestException(" El dentista con id: "+dentistDto.getId() +", que se quiere actualizar no existe.");
        }
            DentistDto dentistDto1 =iDentistService.update(dentistDto);
            return new ResponseEntity<DentistDto>(dentistDto1, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getDentistByName(@PathVariable String name){
        return new  ResponseEntity<List<DentistDto>>(iDentistService.findDentistsByName(name), HttpStatus.OK);
    }

}
