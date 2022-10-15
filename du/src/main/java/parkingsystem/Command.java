package parkingsystem;

import java.util.Properties;

/**
 * This class is responsible providing an interface of
 * commands for the ParkingService
 *
 * @author Erik Grafton
 * @version 1.0
 * @since September 18, 2022
 */

public interface Command {
  public String getCommandName();

  public String getDisplayName();

  public String execute(Properties param) throws Exception;
}
