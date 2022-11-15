package flotte.controller;

import flotte.model.Kilometrage;
import flotte.repository.KilometrageRepository;
import flotte.repository.VehicleRepository;
import flotte.response.FailedResponse;
import flotte.response.SuccessReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class KilometrageController {

    @Autowired
    KilometrageRepository kilometrageRepository;


    @GetMapping(value="/vehicle/{vehicle_id}/kilometrage/{kilometrage_id}")
    public ResponseEntity<?> kilometrageValue(@PathVariable("vehicle_id") Long vehicleId, @PathVariable("kilometrage_id") Long kilometrageId) {

        Optional<Kilometrage> data = kilometrageRepository.findByVehicleIdAndId(vehicleId,kilometrageId);

        if(data.isPresent()) {
            SuccessReponse response = new SuccessReponse(data.get());
            return new ResponseEntity<SuccessReponse>(response, HttpStatus.OK);
        } else {
            FailedResponse response = new FailedResponse(404,"kilometrage ID not found");
            return new ResponseEntity<FailedResponse>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vehicle/{vehicle_id}/kilometrage")
    public String kilometrageList(@PathVariable("vehicle_id") String vehicleId) {
        return null;
    }

    @PostMapping(
            value="/vehicle/{vehicle_id}/kilometrage",
            consumes= {MediaType.APPLICATION_JSON_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE})
    public String kilometrageInsertion(@PathVariable("vehicle_id") Integer vehicleId, @RequestBody Kilometrage kilometrage) {
        return null;
    }


    @PutMapping(
            value="/vehicle/{vehicle_id}/kilometrage/{kilometrage_id}",
            consumes= {MediaType.APPLICATION_JSON_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE})
    public String kilometrageUpdate(@PathVariable("vehicle_id") Integer vehicleId,@PathVariable("kilometrage_id") Integer kilometrageId,@RequestBody Kilometrage kilometrage) {
        return null;
    }

    @DeleteMapping("/vehicle/{vehicle_id}/kilometrage/{kilometrage_id}")
    public String kilometrageDelete(@PathVariable("vehicle_id") Integer vehicleId,@PathVariable("kilometrage_id") Integer kilometrageId) {
        return null;
    }

}
