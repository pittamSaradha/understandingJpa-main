package parkinglot.repositories;
import parkinglot.Gate;
import parkinglot.exceptions.GateNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class GateRepository {
    Map<Long,Gate> gates=new HashMap<>();



    public Gate findGateByGateId(Long gateId) throws GateNotFoundException {
        if(gates.containsKey(gateId)){
            return gates.get(gateId);
        }
        throw new GateNotFoundException();
    }
}
