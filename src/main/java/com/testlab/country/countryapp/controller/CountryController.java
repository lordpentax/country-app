package com.testlab.country.countryapp.controller;

import com.testlab.country.countryapp.dto.CountryDTO;
import com.testlab.country.countryapp.helper.CountryAssembler;
import com.testlab.country.countryapp.service.CountryService;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/country")
public class CountryController {

    private final CountryService countryService;
    private final CountryAssembler countryAssembler;

    public CountryController(final CountryService countryService, final CountryAssembler countryAssembler) {
        this.countryService = countryService;
        this.countryAssembler = countryAssembler;
    }

    @GetMapping("/countries")
    public Resource getAllCountry() {
        return new Resource(countryService.getAllCountries().stream()
                .map(countryDTO -> new Resource<>(countryAssembler.toResource(countryDTO))));
    }

    @PostMapping("/country")
    public Resource createCountry(@RequestBody final CountryDTO countryDTO) {
        return new Resource(countryAssembler.toResource(countryService.createCountry(countryDTO)));
    }

    @GetMapping("/{id}/country")
    public Resource getCountry(@PathVariable final Long id) {
        return new Resource(countryAssembler.toResource(countryService.getASingleCountry(id)));
    }

    @PutMapping("/{id}/country")
    public Resource updateCountry(@PathVariable final long id, @Valid @RequestBody final CountryDTO countryDTO) {
        return new Resource(countryAssembler.toResource(countryService.updateCountry(id, countryDTO)));
    }

    @DeleteMapping("/{id}/country")
    public Resource deleteCountry(@PathVariable final long id) {
        countryService.deleteCountry(id);
        return new Resource(linkTo(methodOn(CountryController.class).getAllCountry()).withRel("countries"));
    }
}
