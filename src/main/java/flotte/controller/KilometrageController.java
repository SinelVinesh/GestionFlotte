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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class KilometrageController {

    @Autowired
    KilometrageRepository kilometrageRepository;
    @Autowired
    VehicleRepository vehicleRepository;


    @GetMapping(value="/vehicle/{vehicle_id}/kilometrage/{kilometrage_id}")
    public ResponseEntity<?> kilometrageValue(@PathVariable("vehicle_id") Long vehicleId, @PathVariable("kilometrage_id") Long kilometrageId) {

        Optional<Kilometrage> data = kilometrageRepository.findByVehicleIdAndId(vehicleId,kilometrageId);

        if(data.isPresent()) {
            SuccessReponse response = new SuccessReponse(data.get());
            return new ResponseEntity<SuccessReponse>(response, HttpStatus.OK);
        } else {
            StatusResponse response = new StatusResponse(404,"kilometrage ID or vehicle ID not found");
            return new ResponseEntity<StatusResponse>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vehicle/{vehicle_id}/kilometrage")
    public ResponseEntity<?> kilometrageList(@PathVariable("vehicle_id") Long vehicleId) {
        Optional<List<Kilometrage>> data = kilometrageRepository.findByVehicleId(vehicleId);
        if(data.isPresent()) {
            SuccessReponse response = new SuccessReponse(data.get());
            return new ResponseEntity<SuccessReponse>(response, HttpStatus.OK);
        } else {
            StatusResponse response = new StatusResponse(404,"vehicle ID not found");
            return new ResponseEntity<StatusResponse>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(
            value="/vehicle/{vehicle_id}/kilometrage",
            consumes= {MediaType.APPLICATION_JSON_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createKilometrage(@PathVariable("vehicle_id") Long vehicleId, @RequestBody Kilometrage kilometrage) {
        try {
            Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
            if(vehicle.isPresent()) {
                kilometrage.setVehicle(vehicle.get());
                Kilometrage _kilometrage = kilometrageRepository.save(kilometrage);
                SuccessReponse response = new SuccessReponse(_kilometrage);
                return new ResponseEntity<SuccessReponse>(response, HttpStatus.CREATED);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            StatusResponse response = new StatusResponse(500,"failed to save the kilometrage");
            return new ResponseEntity<StatusResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(
            value="/vehicle/{vehicle_id}/kilometrage/{kilometrage_id}",
            consumes= {MediaType.APPLICATION_JSON_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> kilometrageUpdate(@PathVariable("vehicle_id") Long vehicleId, @PathVariable("kilometrage_id") Long kilometrageId, @RequestBody Kilometrage kilometrage) {
        try {
            Optional<Kilometrage> data = kilometrageRepository.findByVehicleIdAndId(vehicleId, kilometrageId);
            if(data.isPresent()) {
                Kilometrage _kilometrage = data.get();
                if(kilometrage.getStart() != null) {
                    _kilometrage.setStart(kilometrage.getStart());
                }
                if(kilometrage.getEnd() != null) {
                    _kilometrage.setEnd(kilometrage.getEnd());
                }
                if(kilometrage.getDate() != null) {
                    _kilometrage.setDate(kilometrage.getDate());
                }
                if(kilometrage.getVehicle() != null) {
                    _kilometrage.setVehicle(kilometrage.getVehicle());
                }
                SuccessReponse response = new SuccessReponse(kilometrageRepository.save(_kilometrage));
                return new ResponseEntity<SuccessReponse>(response, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            StatusResponse response = new StatusResponse(500,"failed to update kilometrage");
            return new ResponseEntity<StatusResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/vehicle/{vehicle_id}/kilometrage/{kilometrage_id}")
    public ResponseEntity<StatusResponse> kilometrageDelete(@PathVariable("vehicle_id") Long vehicleId, @PathVariable("kilometrage_id") Long kilometrageId) {
        try {
            kilometrageRepository.deleteByVehicleIdAndId(vehicleId,kilometrageId);
            StatusResponse response = new StatusResponse(204,kilometrageId.toString() +" has been successfully deleted");
            return new ResponseEntity<StatusResponse>(response,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            StatusResponse response = new StatusResponse(500,"failed to delete kilometrage "+ kilometrageId.toString());
            return new ResponseEntity<StatusResponse>(response, HttpStatus.NO_CONTENT);
        }
    }

}
