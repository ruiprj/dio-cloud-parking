package dio.digitalinnovation.parking.controller;

import dio.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import dio.digitalinnovation.parking.controller.dto.ParkingDTO;
import dio.digitalinnovation.parking.controller.mapper.ParkingMapper;
import dio.digitalinnovation.parking.model.Parking;
import dio.digitalinnovation.parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = this.parkingService.findAll();
        List<ParkingDTO> result = this.parkingMapper.toParkingDTOList(parkingList);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        Parking parking = this.parkingService.findById(id);
        ParkingDTO result = this.parkingMapper.toParkingDTO(parking);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        var parkingCreate = this.parkingMapper.toParkingCreate(dto);
        var parking = this.parkingService.create(parkingCreate);
        var result = this.parkingMapper.toParkingDTO(parking);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}