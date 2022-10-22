package charge.strategy.factory;

/**
 * This class is responsible for creating ParkingChargeStrategy
 * instances based on the ChargeStrategy Enumeration that it recieves.
 *
 * @author Erik Grafton
 * @version 1.0
 * @since October 07, 2022
 */

import charge.strategy.ChargeByTypeAndDayStrategy;
import charge.strategy.ChargeByTypeAndTimeStrategy;
import charge.strategy.ParkingChargeStrategy;

public class ParkingChargeStrategyFactory {
  public enum ChargeStrategy {
    TypeAndDay,
    TypeAndTime
  }

  ;

  public ParkingChargeStrategy getChargeStrategy(ChargeStrategy chargeStrategy) {
    switch (chargeStrategy) {
      case TypeAndDay:
        return new ChargeByTypeAndDayStrategy();
      case TypeAndTime:
        return new ChargeByTypeAndTimeStrategy();
      default:
        return null;
    }
  }
}
