package at.kepplinger.brewery.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "BEER")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Storage> storages = new LinkedList<>();

    private String designation;
    private String type;
    private double fillAmount;
    private double price;

    public Beer() {
    }

    public Beer(String designation, String type, double fillAmount, double price) {
        this.designation = designation;
        this.type = type;
        this.fillAmount = fillAmount;
        this.price = price;
    }

    public String getDesignation() {
        return designation;
    }

    public Long getId() {
        return id;
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public String getType() {
        return type;
    }

    public double getFillAmount() {
        return fillAmount;
    }

    public double getPrice() {
        return price;
    }
}
