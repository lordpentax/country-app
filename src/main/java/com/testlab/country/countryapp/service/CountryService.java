package com.testlab.country.countryapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testlab.country.countryapp.dto.CountryDTO;
import com.testlab.country.countryapp.exception.NoResourceFoundException;
import com.testlab.country.countryapp.mapper.CountryMapper;
import com.testlab.country.countryapp.model.Country;
import com.testlab.country.countryapp.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CountryService {

    private final static String MESSAGE = "no country found with this %d id";
    private final CountryRepository countryRepository;
    private final CountryMapper mapper;
    private final ObjectMapper objectmapper;

    public CountryService(final CountryRepository countryRepository, final CountryMapper mapper,
                          final ObjectMapper objectmapper) {
        this.countryRepository = countryRepository;
        this.mapper = mapper;
        this.objectmapper = objectmapper;
    }

    public List<CountryDTO> getAllCountries() {
        return mapper.countriesDto(countryRepository.findAll());
    }

    public CountryDTO createCountry(final CountryDTO countryDTO) {
        Country saveCountry = countryRepository.save(mapper.toCountry(countryDTO));
        return mapper.toCountryDTO(saveCountry);
    }

    public CountryDTO updateCountry(final Long id, final CountryDTO countryDTO) {
        Country updateCountry = countryRepository.findById(id)
                .map(country -> {
                    country.setId(id);
                  return   countryRepository.save(mapper.toCountry(countryDTO));
                }).orElseThrow(() -> new NoResourceFoundException(String.format(MESSAGE, id)));

        return mapper.toCountryDTO(updateCountry);
    }

    public CountryDTO getASingleCountry(final Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new NoResourceFoundException(String.format(MESSAGE, id)));
        return mapper.toCountryDTO(country);
    }

    public void deleteCountry(final Long id) {
        countryRepository.deleteById(id);
        log.info("country with {}: was succesful deleted", id);
    }
}
