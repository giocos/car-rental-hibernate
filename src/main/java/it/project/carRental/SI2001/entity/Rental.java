package it.project.carRental.SI2001.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="rentals")
public class Rental implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="rental_id")
    private int id;

    @Column(name="rental_date")
    private Date rentalDate;

    @OneToOne
    @JoinColumn(referencedColumnName="coupon_id")
    private Coupon coupon;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany(mappedBy="rental", cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Fine> fines;

    @OneToOne
    @JoinColumn(referencedColumnName="plate_number")
    private Car car;

    public Rental() {}

    public Rental(Date rentalDate, Coupon coupon, User user, Car car) {
        this.rentalDate = rentalDate;
        this.coupon = coupon;
        this.user = user;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Fine> getFines() {
        return fines;
    }

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "rental: " + id + " data: " + rentalDate.getTime();
    }
}
