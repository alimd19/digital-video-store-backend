package watcherdvsbackend.app.models;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Users")
public class UserModel {

  @Id
  private String id;
  @NotBlank
  private String fname;
  @NotBlank
  private String lname;
  @NotBlank
  private String username;
  @NotBlank
  private String password;
  @NotBlank
  private String email;


  public UserModel() {
  }

  public UserModel(String id, String fname, String lname, String username, String password, String email) {
    this.id = id;
    this.fname = fname;
    this.lname = lname;
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFname() {
    return this.fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return this.lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
}
