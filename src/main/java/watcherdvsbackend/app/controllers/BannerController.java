package watcherdvsbackend.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import watcherdvsbackend.app.CustomResponse;
import watcherdvsbackend.app.models.Banner;
import watcherdvsbackend.app.services.BannerService;


@RestController
public class BannerController {

  @Autowired
  private BannerService bannerService;

  @GetMapping("/banners")
  public ResponseEntity<CustomResponse<Banner>> getBanners(
      @RequestParam(value = "type", defaultValue = "") String type,
      @RequestParam(value = "name", defaultValue = "") String name,
      @RequestParam(value = "featured", defaultValue = "") String featured) {

    CustomResponse<Banner> response = new CustomResponse<>();

    response.setBody(bannerService.getBannersByType(type));
    response.setMessage("List of requested banners");

    return new ResponseEntity<CustomResponse<Banner>>(response, HttpStatus.OK);
  }
}
