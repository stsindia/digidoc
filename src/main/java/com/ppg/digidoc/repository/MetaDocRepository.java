package com.ppg.digidoc.repository;
 
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ppg.digidoc.models.QRRequest;
public interface MetaDocRepository extends MongoRepository<QRRequest,  String> {
	/*
	 * public QRRequest getInfoById(String Orderid) { return
	 * mongoOperations.findOne( Query.query(Criteria.where("orderID").is(Orderid)),
	 * QRRequest.class, "Data" ); }
	 */
	 @Query("{orderId : ?0}")   
	 public List<QRRequest> findByorderId(String orderId);
	 
	 
	 
	 @Query("{id : ?0}")   
	 public List<QRRequest> findByUId(String id);
	 
	 @Query("{orderId : ?0,application : ?1}")   
		public  List<QRRequest> findByOrderIDAndApp(String orderId, String application) ;
} 