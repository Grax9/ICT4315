package parkingsystem;

import java.security.InvalidParameterException;
import java.util.Properties;

/**
 * This class is responsible for Customer Registration commands from
 * the ParkingService.
 *
 * @author Erik Grafton
 * @version 1.0
 * @since September 18, 2022
 */

public class RegisterCustomerCommand implements Command {
  private ParkingOffice office;

  /* Constructor */
  public RegisterCustomerCommand(ParkingOffice office) {
    this.office = office;
  }

  /* Ensure required params exist */
  private void checkParameters(Properties props) {
    String[] requiredProps = { "name", "address", "phone" };

    for (String prop : requiredProps) {
      if (!props.containsKey(prop)) {
        throw new InvalidParameterException("Error: Missing Property " + prop);
      }
    }
  }

  @Override
  public String getCommandName() {
    return "CUSTOMER";
  }

  @Override
  public String getDisplayName() {
    return "CUSTOMER";
  }

  /* Execute register method in Parking Office */
  @Override
  public String execute(Properties props) {
    // Check params
    checkParameters(props);
    // Register Customer
    Customer customer = office.register(props.getProperty("name"), props.getProperty("address"),
        props.getProperty("phone"));
    return customer.getCustomerID();
  }

}
