package parkinglot.strategies;

import parkinglot.ParkingSlot;
import parkinglot.VehicleType;
import parkinglot.exceptions.ParkingLotFullException;

public interface ParkingPlaceAllotmentStrategy {
    ParkingSlot getParkingSlot(VehicleType vehicleType,Long parkingLotId)
            throws ParkingLotFullException;

}
