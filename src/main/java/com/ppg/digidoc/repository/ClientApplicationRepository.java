package com.ppg.digidoc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ppg.digidoc.models.ApplicationName;
 
 
public interface ClientApplicationRepository extends MongoRepository<ApplicationName, String> {

	/*
	 * @Autowired private final MongoTemplate mongoTemplate=new
	 * MongoTemplate(MongoDatabaseFactory());;
	 */
	@Query("{ApplicationCode : ?0,Auth_Sign : ?1}")   
	public  List<ApplicationName> findByAppandCode(String ApplicationCode, String Auth_Sign) ;
 
	
	@Query("{ApplicationCode : ?0,Auth_Sign : ?1,APIKey : ?2}")   
	public  List<ApplicationName> findByAppandCodeWithKey(String ApplicationCode, String Auth_Sign,String APIKey) ;
}