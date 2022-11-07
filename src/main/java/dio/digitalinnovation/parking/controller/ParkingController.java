package dio.digitalinnovation.parking.controller;

import dio.digitalinnovation.parking.controller.dto.ParkingDTO;
import dio.digitalinnovation.parking.controller.mapper.ParkingMapper;
import dio.digitalinnovation.parking.model.Parking;
import dio.digitalinnovation.parking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public List<ParkingDTO> findAll() {
        List<Parking> parkingList = this.parkingService.findAll();
        List<ParkingDTO> result = this.parkingMapper.toParkingDTOList(parkingList);

        return result;
    }

}
