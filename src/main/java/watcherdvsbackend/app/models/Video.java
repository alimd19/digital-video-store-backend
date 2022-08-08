package watcherdvsbackend.app.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Videos")
public class Video {

  @Id
  private String id;
  @NotBlank
  private String name;
  @NotBlank
  private String synopsis;
  @NotBlank
  private String type;
  @NotBlank
  private String smallPoster;
  @NotBlank
  private String bigPoster;
  @NotNull
  @Min(1)
  private double price;
  @NotNull
  @Min(1)
  private double rent;
  @NotNull
  private Boolean featured;

  public Video() {
  }

  public Video(String id, String name, int price, String synopsis, String type, String smallPoster, String bigPoster,
      int rent, Boolean featured) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.synopsis = synopsis;
    this.type = type;
    this.smallPoster = smallPoster;
    this.bigPoster = bigPoster;
    this.rent = rent;
    this.featured = featured;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getSynopsis() {
    return this.synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSmallPoster() {
    return this.smallPoster;
  }

  public void setSmallPoster(String smallPoster) {
    this.smallPoster = smallPoster;
  }

  public String getBigPoster() {
    return this.bigPoster;
  }

  public void setBigPoster(String bigPoster) {
    this.bigPoster = bigPoster;
  }

  public double getRent() {
    return this.rent;
  }

  public void setRent(double rent) {
    this.rent = rent;
  }

  public Boolean isFeatured() {
    return this.featured;
  }

  public Boolean getFeatured() {
    return this.featured;
  }

  public void setFeatured(Boolean featured) {
    this.featured = featured;
  }

}
