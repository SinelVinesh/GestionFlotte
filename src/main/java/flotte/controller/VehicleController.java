package flotte.controller;

import flotte.model.Image;
import flotte.model.Insurance;
import flotte.model.Vehicle;
import flotte.repository.*;
import flotte.response.StatusResponse;
import flotte.response.SuccessReponse;
import flotte.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class VehicleController {

    @Autowired
    KilometrageRepository kilometrageRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    InsuranceRepository insuranceRepository;

    TokenService tokenService;
    public VehicleController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping(value="/vehicles/{vehicle_id}")
    public ResponseEntity<?> vehicleValue(@PathVariable("vehicle_id") Long vehicleId, @RequestHeader("token") String token) {
        ResponseEntity<?> authorization = tokenService.authenticate(token);
        if(authorization != null) {
            return authorization;
        }
        Optional<Vehicle> data = vehicleRepository.findById(vehicleId);

        if(data.isPresent()) {
            SuccessReponse response = new SuccessReponse(data.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            StatusResponse response = new StatusResponse(404,"vehicle not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vehicles")
    public ResponseEntity<?> vehicleList() {
        List<Vehicle> data = vehicleRepository.findAll();
        for(Vehicle element : data) {
            Optional<Insurance> mostRecent = insuranceRepository.findFirstByVehicleOrderByEndDesc(element);
            if(mostRecent.isPresent()) {
                element.setCurrentInsurance(mostRecent.get());
            }
        }
        SuccessReponse response = new SuccessReponse(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<StatusResponse> vehicleDelete(@PathVariable("vehicle_id") Long vehicleId) {
        try {
            vehicleRepository.deleteById(vehicleId);
            StatusResponse response = new StatusResponse(204, vehicleId +" has been successfully deleted");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            StatusResponse response = new StatusResponse(500,"failed to delete vehicle "+ vehicleId.toString());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/vehicles/{vehicle_id}/image")
    public ResponseEntity<StatusResponse> imageUpload(@RequestBody Vehicle vehicle, @PathVariable Long vehicle_id) {
        try {
            if(!vehicle.getId().equals(vehicle_id)) throw new Exception();
            Optional<Vehicle> data = vehicleRepository.findById(vehicle_id);
            if(data.isPresent()) {
                Vehicle target = data.get();
                Image toSave = vehicle.getImage();
                imageRepository.save(toSave);
                target.setImage(toSave);
                vehicleRepository.save(target);
                return ResponseEntity.ok(new StatusResponse(200, "L'image a bien été sauvegardée"));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            StatusResponse response = new StatusResponse(500,"La sauvegarde de l'image a échoué, veuillez réessayer");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
