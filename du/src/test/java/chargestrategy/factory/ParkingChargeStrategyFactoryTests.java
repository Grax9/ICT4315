package chargestrategy.factory;

/**
* Tests for ParkingChargeStrategyFactoryTests.java
*
* @author  Erik Grafton
* @version 1.0
* @since   October 07, 2022
*/

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

import chargestrategy.ParkingChargeStrategy;
import chargestrategy.factory.ParkingChargeStrategyFactory.ChargeStrategy;
import parkingsystem.Car;
import parkingsystem.CarType;
import parkingsystem.Customer;
import parkingsystem.Money;

public class ParkingChargeStrategyFactoryTests {
  /**
   * Test pricing for SUV's vs Compact cars
   */
  @Test
  public void testChargeByType() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    LocalDate timestamp = LocalDate.now();
    Car car = new Car("permit", timestamp, "license", CarType.SUV, customer);
    Car car2 = new Car("permit1", timestamp, "license", CarType.COMPACT, customer);

    ParkingChargeStrategyFactory strategyFactory = new ParkingChargeStrategyFactory();
    ParkingChargeStrategy chargeStrategy = strategyFactory.getChargeStrategy(ChargeStrategy.TypeAndTime);

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

    ParkingChargeStrategyFactory strategyFactory = new ParkingChargeStrategyFactory();
    ParkingChargeStrategy chargeStrategy = strategyFactory.getChargeStrategy(ChargeStrategy.TypeAndDay);

    OffsetDateTime weekend = OffsetDateTime.of(2022, 10, 2, 1, 0, 0, 0, ZoneOffset.UTC);
    OffsetDateTime weekday = OffsetDateTime.of(2022, 10, 3, 1, 0, 0, 0, ZoneOffset.UTC);
    Money baseMoney = new Money();
    baseMoney.setCents(100);

    Money chargeWeekend = chargeStrategy.calculateCharge(baseMoney, weekend, car);
    Money chargeWeekday = chargeStrategy.calculateCharge(baseMoney, weekday, car);

    assertEquals(0, chargeWeekend.getCents());
    assertEquals(100, chargeWeekday.getCents());
  }

  /**
   * Test pricing for Peak hours vs Off hours
   */
  @Test
  public void testChargeByTime() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    LocalDate timestamp = LocalDate.now();
    Car car = new Car("permit", timestamp, "license", CarType.SUV, customer);

    ParkingChargeStrategyFactory strategyFactory = new ParkingChargeStrategyFactory();
    ParkingChargeStrategy chargeStrategy = strategyFactory.getChargeStrategy(ChargeStrategy.TypeAndTime);

    OffsetDateTime timePeak = OffsetDateTime.of(2022, 10, 2, 10, 0, 0, 0, ZoneOffset.UTC);
    OffsetDateTime timeSlow = OffsetDateTime.of(2022, 10, 2, 20, 0, 0, 0, ZoneOffset.UTC);
    Money baseMoney = new Money();
    baseMoney.setCents(100);

    Money chargeSlow = chargeStrategy.calculateCharge(baseMoney, timeSlow, car);
    Money chargePeak = chargeStrategy.calculateCharge(baseMoney, timePeak, car);

    assertEquals(100, chargeSlow.getCents());
    assertEquals(300, chargePeak.getCents());
  }
}
