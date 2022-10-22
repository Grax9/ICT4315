package chargestrategy.decorator;

/**
 * This class is responsible for time surcharges within
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

public class TimeChargeDecorator extends ParkingChargeCalculatorDecorator {
  public TimeChargeDecorator(ParkingChargeCalculator parkingChargeCalculator) {
    super(parkingChargeCalculator);
  }
  
  @Override
  public Money getParkingCharge(ParkingPermit permit, ParkingLot parkingLot, OffsetDateTime entryTime, OffsetDateTime exitTime) {
    Money rate = super.getParkingCharge(permit, parkingLot, entryTime, exitTime);

    if (entryTime.getHour() > 6 && entryTime.getHour() < 19) {
      // Add two dollar surcharge during peak hours
      rate.setCents(rate.getCents() + 200);
    }
    return rate;
  }
}
