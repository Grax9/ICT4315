package chargestrategy;

import java.time.OffsetDateTime;

import parkingsystem.Car;
import parkingsystem.Money;

public interface ParkingChargeStrategy {
  public Money calculateCharge(Money baseRate, OffsetDateTime entryTime, Car car);
}
