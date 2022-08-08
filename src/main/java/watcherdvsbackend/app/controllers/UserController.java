package watcherdvsbackend.app.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import watcherdvsbackend.app.CustomResponse;
import watcherdvsbackend.app.models.UserModel;
import watcherdvsbackend.app.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<CustomResponse<UserModel>> getUser(@PathVariable("id") String id) {
    CustomResponse<UserModel> response = new CustomResponse<>();
    HttpStatus status;

    try {
      response.setBody(Collections.singletonList(userService.getUserById(id)));
      response.setMessage("UserModel with Id: " + id);
      status = HttpStatus.OK;
    } catch (Exception e) {
      response.setBody(null);
      response.setMessage(e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<CustomResponse<UserModel>>(response, status);
  }
}
