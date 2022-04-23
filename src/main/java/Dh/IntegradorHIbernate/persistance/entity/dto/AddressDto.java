package Dh.IntegradorHIbernate.persistance.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private Long id;

    private String street;

    private int number;

    private String location;

    private String province;

    public AddressDto() {}
}
