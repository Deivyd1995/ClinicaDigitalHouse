package Dh.IntegradorHIbernate.service.impl;



import Dh.IntegradorHIbernate.exceptions.ResourceNotFoundException;
import Dh.IntegradorHIbernate.persistance.entity.Turn;
import Dh.IntegradorHIbernate.persistance.entity.dto.TurnDto;
import Dh.IntegradorHIbernate.persistance.repository.ITurnRepository;
import Dh.IntegradorHIbernate.service.ITurnService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnService implements ITurnService {

    @Autowired
    private ITurnRepository iTurnRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public TurnDto register(TurnDto turnDto) {
        Turn turn = mapper.convertValue(turnDto, Turn.class);
        iTurnRepository.save(turn);
        return mapper.convertValue(turn, TurnDto.class);
    }

    @Override
    public TurnDto findById(Long id) throws ResourceNotFoundException {
        Optional<Turn> turn = iTurnRepository.findById(id);
        if(!turn.isPresent()){
            throw new ResourceNotFoundException("No existe un turno con ID " + id);
        }
        return mapper.convertValue(turn, TurnDto.class);
    }

    @Override
    public void delete(Long id) {
        Optional<Turn> turn = iTurnRepository.findById(id);
        if(turn.isPresent()){
            iTurnRepository.deleteById(id);
        }
    }

    @Override
    public List<TurnDto> findAll() {
        TurnDto turnDto = null;
        List<TurnDto> turnDtoList = new ArrayList<>();
        List<Turn> turnList = iTurnRepository.findAll();
        for (Turn turn : turnList) {
            turnDto = mapper.convertValue(turn, TurnDto.class);
            turnDtoList.add(turnDto);
        }
        return turnDtoList;
    }

    @Override
    public TurnDto update(TurnDto turnDto) {
        Optional<Turn> optionalTurn = mapper.convertValue(turnDto, Optional.class);
        if (optionalTurn.isPresent()){
            Turn turn = mapper.convertValue(optionalTurn, Turn.class);
            return mapper.convertValue(iTurnRepository.save(turn), TurnDto.class);
        }
        return null;
    }

    @Override
    public List<TurnDto> viewAllTurnsForPatient(Long patient_id) {
        List<TurnDto> turnDtoList = new ArrayList<>();
        List<Turn> turnList = iTurnRepository.viewAllTurnsForPatient(patient_id);
        for (Turn turn : turnList) {
            TurnDto turnDto = mapper.convertValue(turn, TurnDto.class);
            turnDtoList.add(turnDto);
        }
        return turnDtoList;
    }
}
