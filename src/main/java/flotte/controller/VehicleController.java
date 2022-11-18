package flotte.controller;

import flotte.model.Kilometrage;
import flotte.model.Vehicle;
import flotte.repository.KilometrageRepository;
import flotte.repository.VehicleRepository;
import flotte.response.StatusResponse;
import flotte.response.SuccessReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VehicleController {

    @Autowired
    KilometrageRepository kilometrageRepository;
    @Autowired
    VehicleRepository vehicleRepository;


    @GetMapping(value="/vehicles/{vehicle_id}")
    public ResponseEntity<?> vehicleValue(@PathVariable("vehicle_id") Long vehicleId) {

        Optional<Vehicle> data = vehicleRepository.findById(vehicleId);

        if(data.isPresent()) {
            SuccessReponse response = new SuccessReponse(data.get());
            return new ResponseEntity<SuccessReponse>(response, HttpStatus.OK);
        } else {
            StatusResponse response = new StatusResponse(404,"vehicle not found");
            return new ResponseEntity<StatusResponse>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vehicles")
    public ResponseEntity<?> vehicleList() {
    List<Vehicle> data = vehicleRepository.findAll();
    SuccessReponse response = new SuccessReponse(data);
    return new ResponseEntity<SuccessReponse>(response, HttpStatus.OK);
    }

    @PostMapping(
            value="/vehicles",
            consumes= {MediaType.APPLICATION_JSON_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle _vehicle = vehicleRepository.save(vehicle);
            SuccessReponse response = new SuccessReponse(_vehicle);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            StatusResponse response = new StatusResponse(500,"failed to save the vehicle");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(
            value="/vehicles/{vehicle_id}",
            consumes= {MediaType.APPLICATION_JSON_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> vehicleUpdate(@PathVariable("vehicle_id") Long vehicleId, @RequestBody Vehicle vehicle) {
        try {
            Optional<Vehicle> data = vehicleRepository.findById(vehicleId);
            if(data.isPresent()) {
                Vehicle _vehicle = data.get();
                if(vehicle.getLicensePlate() != null) {
                    _vehicle.setLicensePlate(vehicle.getLicensePlate());
                }
                SuccessReponse response = new SuccessReponse(vehicleRepository.save(_vehicle));
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            StatusResponse response = new StatusResponse(500,"failed to update vehicle");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/vehicles/{vehicle_id}")
    public ResponseEntity<StatusResponse> VehicleDelete(@PathVariable("vehicle_id") Long vehicleId) {
        try {
            vehicleRepository.deleteById(vehicleId);
            StatusResponse response = new StatusResponse(204,vehicleId.toString() +" has been successfully deleted");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            StatusResponse response = new StatusResponse(500,"failed to delete vehicle "+ vehicleId.toString());
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

}
