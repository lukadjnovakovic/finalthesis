package impl;

import dto.CountryDTO;
import entity.Country;
import mappers.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CountryRepo;
import services.ICountryService;

import java.util.LinkedList;
import java.util.List;

@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    CountryRepo countryRepo;
    @Autowired
    CountryMapper countryMapper;


    @Override
    public List<CountryDTO> returnAllCountries() {
        List<Country> countries = countryRepo.findAll();
        List<CountryDTO> countryDTOS = new LinkedList<>();

        for (Country country : countries) {
                countryDTOS.add(countryMapper.country2DTO(country));
        }
        return countryDTOS;
    }

    @Override
    public CountryDTO returnCountryById(int id) throws Exception {
        Country country = countryRepo.findById(id).orElse(null);
        if (country == null) {
            throw new Exception("No country with given ID");
        }
        return countryMapper.country2DTO(country);
    }
}
