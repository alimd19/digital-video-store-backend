package watcherdvsbackend.app.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import watcherdvsbackend.app.CustomResponse;
import watcherdvsbackend.app.models.Video;
import watcherdvsbackend.app.services.VideoService;



@RestController
public class VideoController {

  @Autowired
  private VideoService videoService;

  @PostMapping(value = "/video", consumes = {
      MediaType.APPLICATION_JSON_VALUE
  })
  public ResponseEntity<CustomResponse<Video>> addVideo(@RequestBody Video video) {

    CustomResponse<Video> response = new CustomResponse<Video>();
    HttpStatus status;

    try {
      response.setBody(Collections.singletonList(videoService.createVideo(video)));
      response.setMessage(video.getName().toUpperCase() + " " + video.getType() + " created successfully!");
      status = HttpStatus.OK;
    } catch (Exception e) {
      response.setBody(null);
      response.setMessage(e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<CustomResponse<Video>>(response, status);
  }

  @GetMapping("/videos")
  public ResponseEntity<CustomResponse<Video>> getVideos(
      @RequestParam(value = "type", defaultValue = "") String type,
      @RequestParam(value = "name", defaultValue = "") String name,
      @RequestParam(value = "featured", defaultValue = "") String featured) {

    HashMap<String, String> requestOptions = new HashMap<>();
    requestOptions.put("name", name);
    requestOptions.put("type", type);
    requestOptions.put("featured", featured);

    CustomResponse<Video> response = new CustomResponse<Video>();

    response.setBody(videoService.getVideos(requestOptions));

    if (response.getBody().size() < 1) {
      response.setMessage("No results found matching the give criteria");
    } else {
      response.setMessage("List of requested videos");
    }

    return new ResponseEntity<CustomResponse<Video>>(response, HttpStatus.OK);
  }

  @GetMapping("/video/{id}")
  public ResponseEntity<CustomResponse<Optional<Video>>> getVideoById(@PathVariable("id") String id) {

    CustomResponse<Optional<Video>> response = new CustomResponse<Optional<Video>>();
    HttpStatus status;

    try {
      response.setBody(Collections.singletonList(videoService.getVideoById(id)));
      response.setMessage("Video with Id: " + id);
      status = HttpStatus.OK;
    } catch (Exception e) {
      response.setBody(null);
      response.setMessage(e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<CustomResponse<Optional<Video>>>(response, status);
  }

  @PutMapping(value = "/video/{id}", consumes = {
      MediaType.APPLICATION_JSON_VALUE
  })
  public ResponseEntity<CustomResponse<Video>> editVideo(@PathVariable("id") String id, @RequestBody Video video) {

    CustomResponse<Video> response = new CustomResponse<Video>();
    HttpStatus status;

    try {
      response.setBody(Collections.singletonList(videoService.updateVideo(id, video)));
      response.setMessage("Video with Id: " + id + " updated successfully!");
      status = HttpStatus.OK;
    } catch (Exception e) {
      response.setBody(null);
      response.setMessage(e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<CustomResponse<Video>>(response, status);
  }

  @DeleteMapping("/video/{id}")
  public ResponseEntity<CustomResponse<Video>> removeVideo(@PathVariable("id") String id) {
    CustomResponse<Video> response = new CustomResponse<>();
    HttpStatus status;

    try {
      videoService.deleteVideo(id);
      response.setMessage("Video with Id: " + id + " deleted successfully!");
      status = HttpStatus.OK;
    } catch (Exception e) {
      response.setMessage(e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }

    response.setBody(null);

    return new ResponseEntity<CustomResponse<Video>>(response, status);
  }
}
