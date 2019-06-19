package dto;

import java.util.List;

public class UserDTO {

    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;

    private List<TicketDTO> tickets;

    public UserDTO() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDTO(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public List<TicketDTO> getTicketEntities() {
        return tickets;
    }

    public void setTicketEntities(List<TicketDTO> ticketEntities) {
        this.tickets = ticketEntities;
    }
}
