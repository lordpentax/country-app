package com.testlab.country.countryapp.mapper;

import com.testlab.country.countryapp.dto.CountryDTO;
import com.testlab.country.countryapp.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CountryMapper {

    List<CountryDTO> countriesDto(final List<Country> countries);

    CountryDTO toCountryDTO(final Country country);

    Country toCountry(final CountryDTO countryDTO);

}
