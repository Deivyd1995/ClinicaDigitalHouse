package Dh.IntegradorHIbernate.service;


import Dh.IntegradorHIbernate.persistance.entity.dto.DentistDto;

import java.util.List;

public interface IDentistService extends ICrudService<DentistDto>{

   List<DentistDto> findDentistsByName(String name);


}
