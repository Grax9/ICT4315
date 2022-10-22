package charge.decorator;

/**
 * This class is the abstract decorator for the parking charge calculator
 *
 * @author Erik Grafton
 * @version 1.0
 * @since October 21, 2022
 */

import parkingsystem.Money;
import parkingsystem.ParkingLot;
import parkingsystem.ParkingPermit;

import java.time.OffsetDateTime;

public abstract class ParkingChargeCalculatorDecorator extends ParkingChargeCalculator {
  protected ParkingChargeCalculator parkingChargeCalculator;

  public ParkingChargeCalculatorDecorator(ParkingChargeCalculator parkingChargeCalculator) {
    this.parkingChargeCalculator = parkingChargeCalculator;
  }

  @Override
  public Money getParkingCharge(
      ParkingPermit permit, ParkingLot parkingLot, OffsetDateTime entryTime, OffsetDateTime exitTime
  ) {
    return this.parkingChargeCalculator.getParkingCharge(permit, parkingLot, entryTime, exitTime);
  }
}
