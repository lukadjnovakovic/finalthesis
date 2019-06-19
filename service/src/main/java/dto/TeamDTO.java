package dto;

public class TeamDTO {

    private Integer id;
    private String name;
    private CountryDTO country;

    public TeamDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public TeamDTO(String name, CountryDTO country) {
        this.name = name;
        this.country = country;
    }
}
