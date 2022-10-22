package chargestrategy.decorator;

/**
 * This class is the main abstract class for Parking Charge Calculations
 *
 * @author Erik Grafton
 * @version 1.0
 * @since October 21, 2022
 */

import parkingsystem.Money;
import parkingsystem.ParkingLot;
import parkingsystem.ParkingPermit;

import java.time.OffsetDateTime;

public abstract class ParkingChargeCalculator {
  public ParkingChargeCalculator() {
  }

  public abstract Money getParkingCharge(
      ParkingPermit permit, ParkingLot parkingLot, OffsetDateTime entryTime, OffsetDateTime exitTime
  );
}
