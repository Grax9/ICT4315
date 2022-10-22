package chargestrategy.decorator;

/**
 * This class is responsible for flat rate calculations
 * the parking charge calculator
 *
 * @author Erik Grafton
 * @version 1.0
 * @since October 21, 2022
 */

import parkingsystem.Money;
import parkingsystem.ParkingLot;
import parkingsystem.ParkingPermit;

import java.time.OffsetDateTime;

public class FlatRateCalculator extends ParkingChargeCalculator {
  @Override
  public Money getParkingCharge(ParkingPermit permit, ParkingLot parkingLot, OffsetDateTime entryTime, OffsetDateTime exitTime) {
    return new Money(parkingLot.getFee());
  }
}
