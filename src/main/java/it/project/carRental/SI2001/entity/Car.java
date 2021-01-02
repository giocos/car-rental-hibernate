package it.project.carRental.SI2001.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="cars")
public class Car implements Serializable {

    @Id
    @Column(name="plate_number", unique=true, columnDefinition="VARCHAR(20)")
    private String plateNumber;

    @Column(name="model")
    private String model;

    @Column(name="manufacturer")
    private String manufacturer;

    @Temporal(TemporalType.DATE)
    @Column(name="enroll_date")
    private Date enrollDate;

    @ManyToOne
    @JoinColumn
    private Category category;

    @OneToOne(mappedBy="car")
    private Rental rental;

    public Car() {}

    public Car(String plateNumber, String model, String manufacturer, Date enrollDate, Category category) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.manufacturer = manufacturer;
        this.enrollDate = enrollDate;
        this.category = category;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    @Override
    public String toString() {
        return manufacturer + " " + model + "\nplateNumber: " + plateNumber;
    }
}
