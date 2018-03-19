package at.kepplinger.brewery.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "BREWERY")
public class Brewery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Storage> storages = new LinkedList<>();

    @NotNull //Bean Validation
    @Size(min = 1, max = 16) //Bean Validation
    private String location;

    @Digits(integer = 4, fraction = 2) //Bean Validation
    private double area;

    public Brewery() {
    }

    public Brewery(String location, double area) {
        this.location = location;
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public double getArea() {
        return area;
    }

    public List<Storage> getStorages() {
        return storages;
    }
}
