package chargestrategy;

/**
* Tests for ChargeByTypeAndDay.java
*
* @author  Erik Grafton
* @version 1.0
* @since   October 02, 2022
*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

import parkingsystem.Car;
import parkingsystem.CarType;
import parkingsystem.Customer;
import parkingsystem.Money;

public class ChargeByTypeAndDayTests {
  /**
   * Test pricing for SUV's vs Compact cars
   */
  @Test
  public void testChargeByType() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    LocalDate timestamp = LocalDate.now();
    Car car = new Car("permit", timestamp, "license", CarType.SUV, customer);
    Car car2 = new Car("permit1", timestamp, "license", CarType.COMPACT, customer);

    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndDayStrategy();
    OffsetDateTime time = OffsetDateTime.of(2022, 10, 3, 20, 0, 0, 0, ZoneOffset.UTC);
    Money baseMoney = new Money();
    baseMoney.setCents(100);

    Money charge1 = chargeStrategy.calculateCharge(baseMoney, time, car);
    Money charge2 = chargeStrategy.calculateCharge(baseMoney, time, car2);

    assertEquals(100, charge1.getCents());
    assertEquals(80, charge2.getCents());
  }

  /**
   * Test Weekend vs Weekday pricing
   */
  @Test
  public void testChargeByDay() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    LocalDate timestamp = LocalDate.now();
    Car car = new Car("permit", timestamp, "license", CarType.SUV, customer);

    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndDayStrategy();
    OffsetDateTime weekend = OffsetDateTime.of(2022, 10, 2, 1, 0, 0, 0, ZoneOffset.UTC);
    OffsetDateTime weekday = OffsetDateTime.of(2022, 10, 3, 1, 0, 0, 0, ZoneOffset.UTC);
    Money baseMoney = new Money();
    baseMoney.setCents(100);

    Money chargeWeekend = chargeStrategy.calculateCharge(baseMoney, weekend, car);
    Money chargeWeekday = chargeStrategy.calculateCharge(baseMoney, weekday, car);

    assertEquals(0, chargeWeekend.getCents());
    assertEquals(100, chargeWeekday.getCents());
  }
}
