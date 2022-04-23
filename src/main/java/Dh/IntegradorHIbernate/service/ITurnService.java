package Dh.IntegradorHIbernate.service;


import Dh.IntegradorHIbernate.persistance.entity.dto.TurnDto;

import java.util.List;


public interface ITurnService extends ICrudService<TurnDto>{

    List<TurnDto> viewAllTurnsForPatient(Long patient_id);


}
