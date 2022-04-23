package Dh.IntegradorHIbernate.service.impl;


import Dh.IntegradorHIbernate.exceptions.ResourceNotFoundException;
import Dh.IntegradorHIbernate.persistance.entity.Dentist;
import Dh.IntegradorHIbernate.persistance.entity.dto.DentistDto;
import Dh.IntegradorHIbernate.persistance.repository.IDentistRepository;
import Dh.IntegradorHIbernate.service.IDentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistService implements IDentistService {

    @Autowired
    private IDentistRepository iDentistRepository;
    @Autowired
     private ObjectMapper mapper;

    @Override
    public DentistDto register(DentistDto dentistDto) {
        Dentist dentist = mapper.convertValue(dentistDto, Dentist.class);
        iDentistRepository.save(dentist);
        return mapper.convertValue(dentist, DentistDto.class);
    }

    @Override
    public DentistDto findById(Long id) throws ResourceNotFoundException {
        DentistDto dentistDto= null;
       Optional<Dentist> dentist = iDentistRepository.findById(id);
         if(!dentist.isPresent()){
            throw new ResourceNotFoundException("No existe un dentista con ID " + id);
         }
         return dentistDto = mapper.convertValue(dentist, DentistDto.class);
    }

    @Override
    public List<DentistDto> findDentistsByName(String name) {
        List<DentistDto> dentistDtoList = new ArrayList<>();
        List<Dentist> dentistList = iDentistRepository.buscarDentist(name);
        for (Dentist dentist : dentistList) {
            DentistDto dentistDto = mapper.convertValue(dentist, DentistDto.class);
            dentistDtoList.add(dentistDto);
        }
        return dentistDtoList;
    }

    @Override
    public void delete(Long id){
        Optional<Dentist> dentist =iDentistRepository.findById(id) ;
       if (!dentist.isPresent()){
           System.out.println("Nada");
       }iDentistRepository.deleteById(id);
    }

    @Override
    public List<DentistDto> findAll() {
        List<DentistDto> dentistDtosList = new ArrayList<>();
         List<Dentist> dentistsList = iDentistRepository.findAll();
        for (Dentist dentist : dentistsList) {
            DentistDto dentistDto = mapper.convertValue(dentist, DentistDto.class);
                dentistDtosList.add(dentistDto);
        }
        return dentistDtosList;
    }

    @Override
    public DentistDto update(DentistDto dentistDto) {
        Dentist dentist= mapper.convertValue(dentistDto, Dentist.class);
        if (iDentistRepository.existsById(dentist.getId())){
            return mapper.convertValue(iDentistRepository.save(dentist), DentistDto.class);
        }
        return null;
    }



}
