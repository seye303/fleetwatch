package org.vans4u.fleetwatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
public class Van {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Van manufacturer is mandatory")
    @Enumerated(EnumType.STRING)
    private VanManufacturer manufacturer;

    @NotNull(message = "Van type is mandatory")
    @Enumerated(EnumType.STRING)
    private VanType type;

    @NotNull(message = "Registration number is mandatory")
    @Pattern(regexp = "[A-Z]{2}[0-9]{2}\s[A-Z]{3}$", message = "Registration number must be of form 'AANN AAA'")
    private String registrationNumber;

    @Min(0)
    @Max(1000000)
    private int mileage = 0;

    public Van() {}

    public Van(VanManufacturer manufacturer, VanType type, String registrationNumber, int mileage) {
        this.manufacturer = manufacturer;
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.mileage = mileage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VanManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(VanManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public VanType getType() {
        return type;
    }

    public void setType(VanType type) {
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Van van)) return false;
        return mileage == van.mileage && manufacturer == van.manufacturer &&
            type == van.type && Objects.equals(registrationNumber, van.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, type, registrationNumber, mileage);
    }

    @Override
    public String toString() {
        return "Van{" +
            "id=" + id +
            ", manufacturer=" + manufacturer +
            ", type=" + type +
            ", registrationNumber='" + registrationNumber + '\'' +
            ", mileage=" + mileage +
            '}';
    }
}
