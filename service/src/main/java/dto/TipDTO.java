package dto;

public class TipDTO {

    private Integer id;
    private String name;

    public TipDTO() {
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

    public TipDTO(String name) {
        this.name = name;
    }
}
