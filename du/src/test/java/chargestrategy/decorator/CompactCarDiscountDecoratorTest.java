package chargestrategy.decorator;

/**
 * Tests for CompactCarDiscountDecorator.java
 *
 * @author Erik Grafton
 * @version 1.0
 * @since October 21, 2022
 */

import org.junit.jupiter.api.Test;
import parkingsystem.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompactCarDiscountDecoratorTest {

  @Test
  void getParkingCharge() {
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 100, ScanType.ENTRY, null);

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("testPermit", LocalDate.now(), "testLicense", CarType.COMPACT, customer);
    LocalDate time = LocalDate.now();
    OffsetDateTime offsetTime = OffsetDateTime.now();

    ParkingPermit permit = new ParkingPermit("testLicense", car, time);

    Money charge = new CompactCarDiscountDecorator(new FlatRateCalculator()).getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(80, charge.getCents());
  }
}