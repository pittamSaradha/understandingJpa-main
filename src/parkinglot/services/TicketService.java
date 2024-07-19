package parkinglot.services;

import parkinglot.*;
import parkinglot.dtos.IssueTicketRequest;
import parkinglot.exceptions.GateNotFoundException;
import parkinglot.exceptions.ParkingLotFullException;
import parkinglot.exceptions.ParkingLotNotFoundException;
import parkinglot.repositories.GateRepository;
import parkinglot.repositories.ParkingLotRepository;
import parkinglot.repositories.TicketRepository;
import parkinglot.repositories.VehicleRepository;
import parkinglot.strategies.ParkingPlaceAllotmentStrategy;
import parkinglot.strategies.ParkingSlotAllotmentStrategyFactory;

import java.util.Date;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, TicketRepository ticketRepository, ParkingLotRepository parkingLotRepository) {
    }

    public Ticket issueTicket(IssueTicketRequest ticketRequest) throws GateNotFoundException, ParkingLotNotFoundException, ParkingLotFullException {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());
        
        Gate gate = gateRepository.findGateByGateId(ticketRequest.getGateId());
        ticket.setEntryGate(gate);
        Vehicle vehicle = vehicleRepository.getVehicleByNumber(ticketRequest.getVehicleNumber());
        if (vehicle==null) {
             vehicle=new Vehicle(ticketRequest.getVehicleType(),ticketRequest.getVehicleNumber(),
                    ticketRequest.getOwnerName());
             vehicleRepository.save(vehicle);
        }
        ticket.setVehicle(vehicle);
        //3
        ParkingLot parkingLot= parkingLotRepository.getParkingLotById(ticketRequest.getParkingLotId());
        ParkingPlaceAllotmentStrategy allotmentStrategy = parkingLot.getAllotmentStrategy();
        ParkingPlaceAllotmentStrategy parkingAllotmentRule = ParkingSlotAllotmentStrategyFactory.
                 getParkingAllotmentStrategyForType(allotmentStrategy);
        ParkingSlot parkingSlot = parkingAllotmentRule.getParkingSlot(ticketRequest.getVehicleType(),
                ticketRequest.getParkingLotId());
        ticket.setParkingSlot(parkingSlot);

       //4
        ticket.setNumber(ticketRequest.getOwnerName()+UUID.randomUUID());
        return ticketRepository.save(ticket);

    }
}
