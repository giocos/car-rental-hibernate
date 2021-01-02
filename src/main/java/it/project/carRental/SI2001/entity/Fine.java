package it.project.carRental.SI2001.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="fines")
public class Fine implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="fine_id")
    private int id;

    @Column(name="type", nullable=false)
    private String type;

    @Column(name="amount", nullable=false)
    private int amount;

    @ManyToOne
    @JoinColumn
    private Rental rental;

    public Fine() {}

    public Fine(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    @Override
    public String toString() {
        return "fine: " + id + " type: " + type;
    }
}
