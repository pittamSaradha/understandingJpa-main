package parkinglot.strategies;

import parkinglot.ParkingSlot;
import parkinglot.VehicleType;
import parkinglot.exceptions.ParkingLotFullException;

public class SimpleParkingLotAllotStrategy implements ParkingPlaceAllotmentStrategy{

    @Override
    public ParkingSlot getParkingSlot(VehicleType vehicleType, Long parkingLotId)
            throws ParkingLotFullException {


        return null;
    }
}
