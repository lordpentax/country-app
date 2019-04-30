package com.testlab.country.countryapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDTO {

    private Long id;
    private String name;
    private String capital;
    @JsonProperty("natives")
    private String demonym;
    private String language;
    private String currency;
    private String goverment;
    private String population;
}
