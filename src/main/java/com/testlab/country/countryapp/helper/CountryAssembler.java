package com.testlab.country.countryapp.helper;

import com.testlab.country.countryapp.controller.CountryController;
import com.testlab.country.countryapp.dto.CountryDTO;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CountryAssembler implements ResourceAssembler<CountryDTO, Resource<CountryDTO>> {
    @Override
    public Resource<CountryDTO> toResource(CountryDTO countryDTO) {
        return new Resource<>(countryDTO,
                linkTo(methodOn(CountryController.class).getCountry(countryDTO.getId())).withRel("change"),
                linkTo(methodOn(CountryController.class).deleteCountry(countryDTO.getId())).withRel("remove"));
    }
}
