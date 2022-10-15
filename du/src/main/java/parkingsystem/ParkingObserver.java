package parkingsystem;

public class ParkingObserver implements ParkingAction {
  private ParkingOffice parkingOffice;

  public ParkingObserver(ParkingOffice parkingOffice) {
    this.parkingOffice = parkingOffice;
  }

  @Override
  public void notify(ParkingEvent event) {
    parkingOffice.park(event);
  }
}
