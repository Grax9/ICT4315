package parkingsystem;

import java.security.InvalidParameterException;
import java.util.Properties;

/**
 * This class is responsible for Car Registration commands from the
 * parking service
 *
 * @author Erik Grafton
 * @version 1.0
 * @since September 18, 2022
 */

public class RegisterCarCommand implements Command {
  private ParkingOffice office;

  // Constructor
  public RegisterCarCommand(ParkingOffice office) {
    this.office = office;
  }

  /* Check to ensure the necessary parameters exist */
  private void checkParameters(Properties props) throws InvalidParameterException {
    String[] requiredProps = { "name", "address", "phone", "license", "carType" };

    for (String prop : requiredProps) {
      if (!props.containsKey(prop)) {
        throw new InvalidParameterException("Error: Missing Property " + prop);
      }
    }
  }

  @Override
  public String getCommandName() {
    return "CAR";
  }

  @Override
  public String getDisplayName() {
    return "CAR";
  }

  /* Execute the register method in the Parking Office */
  @Override
  public String execute(Properties props) throws InvalidParameterException {
    // Check params
    checkParameters(props);

    // Check if customer exists. Create if not exists.
    Customer customer = office.getCustomer(props.getProperty("name"));
    if (customer == null) {
      customer = office.register(props.getProperty("name"), props.getProperty("address"),
          props.getProperty("phone"));
    }

    /* Map Car Type string to CarType object */
    CarType carType = CarType.valueOf(props.getProperty("carType"));

    /* Register car with parking office and return permit */
    Car car = office.register(customer, props.getProperty("license"), carType);
    return car.getPermit();
  }
}
