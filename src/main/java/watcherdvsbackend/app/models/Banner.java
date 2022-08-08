package watcherdvsbackend.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Banners")
public class Banner {
  
  @Id
  private String id;
  private String type;
  private String img;
  
  public String getId() {
    return id;
  }
  public String getType() {
    return type;
  }
  public String getImg() {
    return img;
  }
}
