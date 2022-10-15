package parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import chargestrategy.ChargeByTypeAndTimeStrategy;
import chargestrategy.ParkingChargeStrategy;

/**
 * Unit Tests for ParkingLot.java
 *
 * @author Erik Grafton
 * @version 1.0
 * @since July 10, 2021
 */
public class ParkingLotTests {
  /**
   * Test Constructor
   */
  @Test
  public void testParkingLot() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    assertEquals("Lot A", parkingLot.getLotID());
    assertEquals("123 Main St.", parkingLot.getAddress());
    assertEquals(0, parkingLot.getCapacity());
    assertEquals(0, parkingLot.getFee());
    assertEquals(ScanType.ENTRY, parkingLot.getScanType());
  }

  /**
   * Test Entry
   */
  @Test
  public void testEntry() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 100, ScanType.ENTRY, chargeStrategy);
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    LocalDate timestamp = LocalDate.now();
    Car car = new Car("permit", timestamp, "license", CarType.SUV, customer);
    Car car2 = new Car("permit1", timestamp, "license", CarType.COMPACT, customer);

    ParkingCharge charge = parkingLot.entry(car, OffsetDateTime.now());
    assertEquals(charge.getPermitID(), car.getPermit());
    assertEquals("$1.00", charge.getAmount().toString());

    ParkingCharge charge2 = parkingLot.entry(car2, OffsetDateTime.now());
    assertEquals(charge2.getPermitID(), car2.getPermit());
    assertEquals("$0.80", charge2.getAmount().toString());
  }

  /**
   * Test toString
   */
  @Test
  public void testToString() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    String expected = "{" +
        " lotID='Lot A'" +
        ", address='123 Main St.'" +
        ", capacity='0'" +
        ", fee='0'" +
        ", scanType='ENTRY'" +
        "}";

    assertEquals(expected, parkingLot.toString());
  }

  /**
   * Test Equals
   */
  @Test
  public void testEquals() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    ParkingLot parkingLot2 = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    ParkingLot parkingLot3 = new ParkingLot("Lot B", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);

    assertTrue(parkingLot.equals(parkingLot2));
    assertFalse(parkingLot.equals(parkingLot3));
  }

  /**
   * Test HashCode
   */
  @Test
  public void testHashCode() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    ParkingLot parkingLot2 = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    ParkingLot parkingLot3 = new ParkingLot("Lot B", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);

    assertEquals(parkingLot.hashCode(), parkingLot2.hashCode());
    assertNotEquals(parkingLot.hashCode(), parkingLot3.hashCode());
  }

  /**
   * Test get/set lotID
   */
  @Test
  public void testGetSetLotID() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    parkingLot.setLotID("test");
    assertEquals("test", parkingLot.getLotID());
  }

  /**
   * Test get/set Address
   */
  @Test
  public void testGetSetAddress() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    parkingLot.setAddress("test");
    assertEquals("test", parkingLot.getAddress());
  }

  /**
   * Test get/set Capacity
   */
  @Test
  public void testGetSetCapacity() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    parkingLot.setCapacity(99);
    assertEquals(99, parkingLot.getCapacity());
  }

  /**
   * Test get/set fee
   */
  @Test
  public void testGetSetFee() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    parkingLot.setFee(1);
    assertEquals(1, parkingLot.getFee());
  }

  /**
   * Test get/set lotID
   */
  @Test
  public void testGetSetScanType() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    parkingLot.setScanType(ScanType.ENTRYEXIT);
    assertEquals(ScanType.ENTRYEXIT, parkingLot.getScanType());
  }
}
