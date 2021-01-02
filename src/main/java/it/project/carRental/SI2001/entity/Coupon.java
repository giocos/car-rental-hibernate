package it.project.carRental.SI2001.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="coupons")
public class Coupon implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="coupon_id")
    private int id;

    @Column(name="amount")
    private int amount;

    @OneToOne(mappedBy="coupon")
    private Rental rental;

    public Coupon() {}

    public Coupon(int amount, Rental rental) {
        this.amount = amount;
        this.rental = rental;
    }

    public int getId() {
        return id;
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
        return "coupon: " + id + "amount: " + amount + "€ rental: " + rental.getId();
    }
}
