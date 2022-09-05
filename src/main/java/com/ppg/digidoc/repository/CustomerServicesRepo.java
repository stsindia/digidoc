package com.ppg.digidoc.repository;
  
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.ppg.digidoc.models.CustomerServices; 
public interface CustomerServicesRepo extends MongoRepository<CustomerServices,  String> {
	 
	 
	
	 
	 public List<CustomerServices> findByorderId(String orderId);
		/*
		 * public List<CustomerServices> findByorderId(String orderId);
		 * 
		 * public List<CustomerServices> findByorderIdandCode(String orderId, String
		 * code);
		 */
		/*
		 * 
		 * 
		 * @Override public void updateStatus(String orderId, String code) { Query query
		 * = new Query().addCriteria(where("_id").is(id));
		 * 
		 * Update update = new Update(); update.set("title", title);
		 * mongoTemplate.update(Doc.class).matching(query).apply(update).first(); }
		 */
	 
} 