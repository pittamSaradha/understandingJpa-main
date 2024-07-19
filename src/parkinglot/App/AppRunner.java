package parkinglot.App;

import parkinglot.VehicleType;
import parkinglot.controllers.TicketController;
import parkinglot.dtos.IssueTicketRequest;
import parkinglot.dtos.IssueTicketResponse;
import parkinglot.repositories.GateRepository;
import parkinglot.repositories.ParkingLotRepository;
import parkinglot.repositories.TicketRepository;
import parkinglot.repositories.VehicleRepository;
import parkinglot.services.TicketService;

public class AppRunner {
    public static void main(String[] args) {
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        TicketService ticketService = new TicketService(gateRepository, vehicleRepository,
                ticketRepository, parkingLotRepository);
        TicketController ticketController = new TicketController(ticketService);
        //IssueRequest request=new IssueTicketRequest(VehicleType.FOUR_WHEELER,"KA-02,MB-1234",
              //  "Keerthi",gateId,parkingLotId)
        //ticketController.issueTicket(request);



    }
}
