package services;

import dto.CountryDTO;

import java.util.List;

public interface ICountryService {

    List<CountryDTO> returnAllCountries();

    CountryDTO returnCountryById(int id) throws Exception;
}
