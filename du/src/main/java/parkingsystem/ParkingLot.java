package parkingsystem;

import java.util.Objects;

import chargestrategy.ParkingChargeStrategy;

import java.time.Instant;
import java.time.OffsetDateTime;

/**
 * This class is responsible for ParkingLot objects
 *
 * @author Erik Grafton
 * @version 1.0
 * @since July 11, 2021
 */

public class ParkingLot {
  private String lotID;
  private String address;
  private Integer capacity;
  private Integer fee;
  private ScanType scanType;
  private ParkingChargeStrategy chargeStrategy;

  /* Creates a new ParkingLot object */
  public ParkingLot(String lotID, String address, Integer capacity, Integer fee, ScanType scanType,
      ParkingChargeStrategy chargeStrategy) {
    this.lotID = lotID;
    this.address = address;
    this.capacity = capacity;
    this.fee = fee;
    this.scanType = scanType;
    this.chargeStrategy = chargeStrategy;
  }

  /*
   * The primary method of the Parking Lot, used to calculate a ParkingCharge upon
   * entry
   */
  public ParkingCharge entry(Car car, OffsetDateTime time) {
    Money money = new Money();
    money.setCents(fee);
    money = chargeStrategy.calculateCharge(money, time, car);
    ParkingCharge charge = new ParkingCharge(car.getPermit(), this.lotID, Instant.now(), money);
    return charge;
  }

  /* Getters / Setters */

  public ParkingChargeStrategy getChargeStrategy() {
    return this.chargeStrategy;
  }

  public void setChargeStrategy(ParkingChargeStrategy chargeStrategy) {
    this.chargeStrategy = chargeStrategy;
  }

  public String getLotID() {
    return this.lotID;
  }

  public void setLotID(String lotID) {
    this.lotID = lotID;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getCapacity() {
    return this.capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public Integer getFee() {
    return this.fee;
  }

  public void setFee(Integer fee) {
    this.fee = fee;
  }

  public ScanType getScanType() {
    return this.scanType;
  }

  public void setScanType(ScanType scanType) {
    this.scanType = scanType;
  }

  /* Override To String */
  @Override
  public String toString() {
    return "{" +
        " lotID='" + getLotID() + "'" +
        ", address='" + getAddress() + "'" +
        ", capacity='" + getCapacity() + "'" +
        ", fee='" + getFee() + "'" +
        ", scanType='" + getScanType() + "'" +
        "}";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    ParkingLot parkingLot = (ParkingLot) obj;
    return (this.lotID == parkingLot.lotID &&
        this.address == parkingLot.address &&
        this.capacity == parkingLot.capacity &&
        this.fee == parkingLot.fee &&
        this.scanType == parkingLot.scanType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lotID, address, capacity, fee, scanType);
  }

}
