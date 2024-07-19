package parkinglot.controllers;

import parkinglot.dtos.IssueTicketRequest;
import parkinglot.dtos.IssueTicketResponse;
import parkinglot.dtos.ResponseStatus;
import parkinglot.exceptions.GateNotFoundException;
import parkinglot.exceptions.ParkingLotFullException;
import parkinglot.exceptions.ParkingLotNotFoundException;
import parkinglot.services.TicketService;
import parkinglot.Ticket;

public class TicketController {
    private TicketService ticketService;
    public static String TICKET_ISSUED_MESSAGE="Ticket issued";
    public static String INVALID_GATE="Gate is not valid ";
    public static String INVALID_PARKING_LOT="Parking Lot is not valid ";
    public static String PARKING_LOT_FULL="Parking Lot is full, find a place on a road, ";

    public TicketController(TicketService ticketService) {
    }


    public IssueTicketResponse issueTicket(IssueTicketRequest ticketRequest){
        Ticket ticket = null;
        try {
            ticket = ticketService.issueTicket(ticketRequest);
        } catch (GateNotFoundException e) {
            return new IssueTicketResponse(null, ResponseStatus.FAILURE,INVALID_GATE);
        } catch (ParkingLotNotFoundException e) {
            return new IssueTicketResponse(null, ResponseStatus.FAILURE,INVALID_PARKING_LOT);

        } catch (ParkingLotFullException e) {
            return new IssueTicketResponse(null, ResponseStatus.FAILURE,PARKING_LOT_FULL);

        }
        return new IssueTicketResponse(ticket, ResponseStatus.SUCESS, TICKET_ISSUED_MESSAGE);

    }
}
