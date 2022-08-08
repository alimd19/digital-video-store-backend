package watcherdvsbackend.app;

import java.util.List;

public class CustomResponse<T> {
  private String message;
  private List<T> body;

  public CustomResponse() {
  }

  public CustomResponse(String message, List<T> body) {
    this.message = message;
    this.body = body;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<T> getBody() {
    return this.body;
  }

  public void setBody(List<T> body) {
    this.body = body;
  }
}
