package watcherdvsbackend.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import watcherdvsbackend.app.models.Video;
import watcherdvsbackend.app.models.VideoRepository;

@Service
public class VideoService {
  private Criteria getQuery(Map.Entry<String, String> option) {
    Criteria criteria = new Criteria();
    switch (option.getKey()) {
      case "name":
        criteria = criteria.and("name").regex(Pattern.compile(option.getValue(), Pattern.CASE_INSENSITIVE));
        break;
      case "type":
        criteria = criteria.and("type").is(option.getValue());
        break;
      case "featured":
        criteria = criteria.and("featured").is(Boolean.parseBoolean(option.getValue()));
        break;
    }

    return criteria;
  }

  @Autowired
  private VideoRepository videoRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  public Video createVideo(Video video) throws Exception {
    Video newVideo;
    try {
      newVideo = videoRepository.insert(video);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }

    return newVideo;
  }

  public List<Video> getVideos(HashMap<String, String> options) {
    Query query = new Query();

    for (Map.Entry<String, String> option : options.entrySet()) {
      if (!option.getValue().isEmpty()) {
        query.addCriteria(getQuery(option));
      }
    }

    return mongoTemplate.find(query, Video.class);
  }

  public Optional<Video> getVideoById(String id) throws Exception {
    Optional<Video> video = videoRepository.findById(id);

    if (!video.isPresent()) {
      throw new Exception("Video with Id: " + id + " not found");
    }

    return video;
  }

  public Video updateVideo(String id, Video video) throws Exception {
    Optional<Video> videoToUpdate = videoRepository.findById(id);

    if (!videoToUpdate.isPresent()) {
      throw new Exception("Video with Id: " + id + " not found");
    }

    videoToUpdate.get().setName(video.getName());
    videoToUpdate.get().setSynopsis(video.getSynopsis());
    videoToUpdate.get().setType(video.getType());
    videoToUpdate.get().setSmallPoster(video.getSmallPoster());
    videoToUpdate.get().setBigPoster(video.getBigPoster());
    videoToUpdate.get().setPrice(video.getPrice());
    videoToUpdate.get().setRent(video.getRent());
    videoToUpdate.get().setFeatured(video.getFeatured());

    Video updatedVideo = videoRepository.save(videoToUpdate.get());

    return updatedVideo;
  }

  public void deleteVideo(String id) throws Exception {
    try {
      videoRepository.deleteById(id);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
}
