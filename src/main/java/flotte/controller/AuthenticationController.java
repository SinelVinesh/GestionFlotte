package flotte.controller;

import flotte.model.User;
import flotte.response.StatusResponse;
import flotte.response.SuccessReponse;
import flotte.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        String token= userService.login(user);
        HashMap<String,String> responseData = new HashMap<>();
        responseData.put("token",token);
        if(token != null){
            SuccessReponse response = new SuccessReponse(responseData);
            return new ResponseEntity<SuccessReponse>(response, HttpStatus.OK);
        } else {
            StatusResponse response = new StatusResponse(404,"user not found");
            return new ResponseEntity<StatusResponse>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("token") String token) {
        if(userService.logout(token)) {
            StatusResponse response = new StatusResponse(200, "successfully logout");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            StatusResponse response = new StatusResponse(500, "failed to logout");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
