package Dh.IntegradorHIbernate.service.impl;


import Dh.IntegradorHIbernate.persistance.entity.Address;
import Dh.IntegradorHIbernate.persistance.entity.dto.AddressDto;
import Dh.IntegradorHIbernate.persistance.repository.IAddressRepository;
import Dh.IntegradorHIbernate.service.IAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository iAddressRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public AddressDto register(AddressDto addressDto) {
        Address address = mapper.convertValue(addressDto, Address.class);
        iAddressRepository.save(address);
        return mapper.convertValue(address, AddressDto.class);
    }

    @Override
    public AddressDto findById(Long id) {
        Optional<Address> address = iAddressRepository.findById(id);
        if(address.isPresent()){
          return mapper.convertValue(address, AddressDto.class);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (iAddressRepository.findById(id)==null){
            iAddressRepository.deleteById(id);
        }
    }

    @Override
    public List<AddressDto> findAll() {
        List<AddressDto> addressDtoList = new ArrayList<>();
        List<Address> addressList = iAddressRepository.findAll();
        for (Address address : addressList) {
            AddressDto  addressDto = mapper.convertValue(address, AddressDto.class);
            addressDtoList.add(addressDto);
        }
        return addressDtoList;
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        Optional<Address> optionalAddress = mapper.convertValue(addressDto, Optional.class);
        if (optionalAddress.isPresent()){
            Address address = mapper.convertValue(optionalAddress, Address.class);
            return mapper.convertValue(iAddressRepository.save(address), AddressDto.class);
        }
        return null;
    }
}
