package watcherdvsbackend.app.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import watcherdvsbackend.app.CustomResponse;
import watcherdvsbackend.app.models.UserModel;
import watcherdvsbackend.app.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/signup", consumes = {
      MediaType.APPLICATION_JSON_VALUE
  })
  public ResponseEntity<CustomResponse<UserModel>> addUser(@RequestBody UserModel user) {
    CustomResponse<UserModel> response = new CustomResponse<>();
    HttpStatus status;

    try {
      UserModel newUser = userService.createUser(user);
      response.setBody(Collections.singletonList(newUser));
      response.setMessage("New user created successfully!");
      status = HttpStatus.OK;
    } catch (Exception e) {
      response.setBody(null);
      response.setMessage(e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<CustomResponse<UserModel>>(response, status);
  }
}
