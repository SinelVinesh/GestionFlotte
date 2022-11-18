package flotte.controller;

import flotte.model.User;
import flotte.response.StatusResponse;
import flotte.response.SuccessReponse;
import flotte.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
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
}
