package com.testlab.country.countryapp.repository;

import com.testlab.country.countryapp.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
