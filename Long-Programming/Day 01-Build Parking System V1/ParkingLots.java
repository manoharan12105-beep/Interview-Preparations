package Day_03_24_2026_Build_Parking_System_V1;

public class ParkingLots {
  String[] slots = new String[100];

  public int parkVehicle(String vehicleNumber, String vehicleType, String ownerName, int currentSlot, int totalSlots) {
    if(currentSlot > totalSlots) {
      System.out.println("Parking lot is full. Cannot park the vehicle.");
    }

    Slot slot = new Slot();
    slot.setSlotNumber(currentSlot);
    slot.setOccupied(true);

    Vehicle vehicle = new Vehicle();
    vehicle.setVehicleNumber(vehicleNumber);
    vehicle.setVehicleType(vehicleType);
    vehicle.setOwnerName(ownerName);
    slot.setVehicle(vehicle);
    System.out.println("Vehicle parked at slot number: " + currentSlot);

    slots[currentSlot - 1] = vehicleNumber;
    currentSlot++;
    return currentSlot;

  }

  public void unparkVehicle(String vehicleNumber, int currentSlot, int totalSlots) {
    for (int i = 0; i < totalSlots; i++) {
      if (slots[i] != null && slots[i].equals(vehicleNumber)) {
        slots[i] = null;
        System.out.println("Vehicle with number " + vehicleNumber + " has been unparked.");
        return;
      }
    }
    System.out.println("Vehicle with number " + vehicleNumber + " is not parked.");

  }
}
