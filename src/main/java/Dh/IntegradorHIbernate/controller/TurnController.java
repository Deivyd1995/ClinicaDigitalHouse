package Dh.IntegradorHIbernate.controller;


import Dh.IntegradorHIbernate.exceptions.BadRequestException;
import Dh.IntegradorHIbernate.exceptions.ResourceNotFoundException;
import Dh.IntegradorHIbernate.persistance.entity.dto.TurnDto;
import Dh.IntegradorHIbernate.service.ITurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turn")
public class TurnController {

    @Autowired
    private ITurnService iTurnService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody TurnDto turn) throws BadRequestException {
        try {
            TurnDto turnDto = iTurnService.register(turn);
            return new ResponseEntity<TurnDto>(turnDto, HttpStatus.CREATED);
        }catch (Exception e){
            throw new BadRequestException("Error al seleccionar un paciento o dentista "+ e.getMessage());
        }

    }

    @GetMapping("/id/{turnId}")
    public ResponseEntity<?> search(@PathVariable Long turnId) throws ResourceNotFoundException {
        TurnDto turnDto = iTurnService.findById(turnId);
        return new ResponseEntity<TurnDto>(turnDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{turnoId}")
    public ResponseEntity<?> delete(@PathVariable Long turnoId){
        iTurnService.delete(turnoId);
        return  ResponseEntity.ok("Turn Delete complete");
    }

    @GetMapping("/viewAll")
    public List<TurnDto> getTurns() {
        return iTurnService.findAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody TurnDto turnDto) {
        TurnDto turnDto1 = iTurnService.update(turnDto);
        return new ResponseEntity<TurnDto>(turnDto1, HttpStatus.OK);
    }

    @GetMapping("/patient/{patient_id}")
    public ResponseEntity<?> viewAllTurnsByPatient(@PathVariable Long patient_id){
        return new ResponseEntity<List<TurnDto>>(iTurnService.viewAllTurnsForPatient(patient_id), HttpStatus.OK);
    }
}
