package parkingsystem;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class is responsible for Car objects
 *
 * @author Erik Grafton
 * @version 1.0
 * @since July 11, 2021
 */

public class Car {
  private String permit;
  private LocalDate permitExpiration;
  private String license;
  private CarType type;
  private Customer owner;

  /* Creates a new Car */
  public Car(String permit, LocalDate permitExpiration, String license, CarType type, Customer owner) {
    this.permit = permit;
    this.permitExpiration = permitExpiration;
    this.license = license;
    this.type = type;
    this.owner = owner;
  }

  /* Getters / Setters */
  public String getPermit() {
    return this.permit;
  }

  public void setPermit(String permit) {
    this.permit = permit;
  }

  public LocalDate getPermitExpiration() {
    return this.permitExpiration;
  }

  public void setPermitExpiration(LocalDate permitExpiration) {
    this.permitExpiration = permitExpiration;
  }

  public String getLicense() {
    return this.license;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public CarType getType() {
    return this.type;
  }

  public void setType(CarType type) {
    this.type = type;
  }

  public Customer getOwner() {
    return this.owner;
  }

  public void setOwner(Customer owner) {
    this.owner = owner;
  }

  /* Override toString() */
  @Override
  public String toString() {
    return "{" +
        " permit='" + getPermit() + "'" +
        ", permitExpiration='" + getPermitExpiration() + "'" +
        ", license='" + getLicense() + "'" +
        ", type='" + getType() + "'" +
        ", owner='" + getOwner() + "'" +
        "}";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    Car car = (Car) obj;

    return (car.permit == this.permit &&
        car.permitExpiration == this.permitExpiration &&
        car.license == this.license &&
        car.type == this.type &&
        car.owner == this.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(permit, permitExpiration, license, type, owner);
  }

}
