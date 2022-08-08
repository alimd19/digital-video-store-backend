package watcherdvsbackend.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import watcherdvsbackend.app.models.Banner;

@Service
public class BannerService {

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<Banner> getBannersByType(String type){
    Query query = new Query();
    Criteria criteria = new Criteria().and("type").is(type);
    query.addCriteria(criteria);

    return mongoTemplate.find(query, Banner.class);
  }
}
