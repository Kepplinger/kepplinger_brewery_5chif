package at.kepplinger.brewery.entity;

import javax.persistence.*;

@Entity
@Table(name = "STORAGE")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Brewery brewery;

    @ManyToOne
    private Beer beer;

    private String section;
    private String type;
    private double capacaty;
    private double filled;

    public Storage() {
    }

    public Storage(String section, String type, double capacaty, double filled) {
        this.section = section;
        this.type = type;
        this.capacaty = capacaty;
        this.filled = filled;
    }

    public Storage(Brewery brewery, Beer beer, String section, String type, double capacaty, double filled) {
        this.brewery = brewery;
        this.beer = beer;
        this.section = section;
        this.type = type;
        this.capacaty = capacaty;
        this.filled = filled;
    }

    public Long getId() {
        return id;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public Beer getBeer() {
        return beer;
    }

    public String getSection() {
        return section;
    }

    public String getType() {
        return type;
    }

    public double getCapacaty() {
        return capacaty;
    }

    public double getFilled() {
        return filled;
    }
}
