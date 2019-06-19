package entity;

import javax.persistence.*;

@Entity
@Table(name = "competition")
public class Competition {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String name;

    public Competition() {
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

    public Competition(String name) {
        this.name = name;
    }
}
