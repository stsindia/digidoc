package com.ppg.digidoc.controllers;

import java.util.Base64;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppg.digidoc.models.ApplicationName;
import com.ppg.digidoc.models.CustomerServices;
import com.ppg.digidoc.models.QRRequest;
import com.ppg.digidoc.repository.ApplicationNameRepo;
import com.ppg.digidoc.repository.CustomerServicesRepo;
import com.ppg.digidoc.repository.MetaDocRepository;
import com.ppg.digidoc.services.QRCodeGenerator;

import lombok.extern.slf4j.Slf4j;

@EnableMongoRepositories
@RestController
 
@Slf4j
@CrossOrigin(origins="*")
public class MainController {
	 
	@Autowired
	private MongoTemplate mt;
	private QRCodeGenerator _qrCodeGen;
	@Autowired
	private MetaDocRepository docRe;
	@Autowired
	private CustomerServicesRepo docCS;
	@Autowired
    private ApplicationNameRepo AppName_repository;
	
	@Autowired
	
	public MainController(QRCodeGenerator qrCodeGenerator) {
		_qrCodeGen = qrCodeGenerator;

	}

	@SuppressWarnings("unused")
	@GetMapping("/generate")
	
	// ??? discuss why is this Getmapping
	//  public ResponseEntity<byte[]> getQRCode(QRRequest qrRequestParams) {
	  public ResponseEntity<String> getQRCode(QRRequest qrRequestParams) {
		log.info(qrRequestParams.toString());
		// check if Request Parameters are valid or not. return from here if not valid

		if (qrRequestParams == null) {
			return ResponseEntity.badRequest().header(null).body(null);
		}

		// although we have application field but we will create a rule where every
		// application must send first 3 prefix char to make it a unique value in id
		// field
		// e.g Teco App will send it like Tec1 , Tec2, Tec3 ..... eGate App will send
		// Egt1, Egt2, egt3
		// we will only allow the predefined apps to access the API.

		String orderId = qrRequestParams.getorderId();
		String key = qrRequestParams.getkey();
		String application = qrRequestParams.getApplication();
		String auth_sign = qrRequestParams.getauth_sign();
		try {
			// validations start here
			if ((orderId == null) || (key == null) || (application == null)|| (auth_sign == null)) {
				return new ResponseEntity<>("OrderID/APPKey/Application/auth_sign can not be blank!", HttpStatus.BAD_REQUEST);
				//return ResponseEntity.badRequest().header(null).body(null);

			}

			// Check if valid API key provided//

			// Check if valid Application name provided//
			// validations end here
		} catch (Exception e) {

			return new ResponseEntity<>("OrderID/APPKey/Application/auth_sign can not be blank!", HttpStatus.BAD_REQUEST);
		}

		List<ApplicationName> AppList = AppName_repository.findByAppandCodeWithKey(application,auth_sign,key);
		  Boolean isValid = true;
		  if (AppList.isEmpty()) {
			  return new ResponseEntity<>("Invalid credentials!", HttpStatus.BAD_REQUEST);
		  }
		  
		  List<QRRequest> qrRequestList = docRe.findByOrderIDAndApp(orderId,application);
			 
			  if (!qrRequestList.isEmpty()) {
				  return new ResponseEntity<>("This OrderID/JobID is already issued for given Application! Please Change the combination", HttpStatus.BAD_REQUEST);
			  }
			  
		  
		/*
		 * QRRequest qrRequestIns = new
		 * QRRequest(qrRequestParams.getorderId(),qrRequestParams.getAapplication()
		 * ,qrRequestParams.getkey(), qrRequestParams.getcenterLogo(),
		 * qrRequestParams.geturlContentView(),
		 * qrRequestParams.getContentType(),qrRequestParams.getcontent());
		 * 
		 * 
		 * //insert into database here try { docRe.save(qrRequestIns); } catch
		 * (Exception e) {
		 * 
		 * e.printStackTrace(); }
		 */

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		
		  String encodeToString = Base64.getEncoder().encodeToString(this._qrCodeGen.generate(qrRequestParams).getQrImage());
		 return ResponseEntity.ok().headers(headers).body( encodeToString);
		
		//  return ResponseEntity.ok().headers(headers).body(this._qrCodeGen.generate(qrRequestParams).getQrImage());
	}

	
	/*
	 * 
	 * @GetMapping("/scan") public ResponseEntity<QRRequest> scanQRCode(String
	 * qrContent) { var qrFound = this._qrCodeGen.getQRContent(qrContent); return
	 * qrFound.map(qrRequest -> new ResponseEntity<>(qrRequest, HttpStatus.OK))
	 * .orElseGet(() -> new ResponseEntity<>((QRRequest) null,
	 * HttpStatus.NOT_FOUND)); }
	 */

	@GetMapping("/getByorderId")
 
	public ResponseEntity<QRRequest> InfoById(String orderId, String key, String application, String auth_sign) {
		List<ApplicationName> AppList = AppName_repository.findByAppandCodeWithKey(application,auth_sign,key);
		 
		  if (AppList.isEmpty()) {
			  return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		  }
		  else {
		
		QRRequest qrRequest;
		List<QRRequest> qrRequestList = docRe.findByUId(orderId);

		if (qrRequestList.size() > 0) {

			qrRequest = qrRequestList.get(0);
			return new ResponseEntity<>(qrRequest, HttpStatus.OK);
		}

		else

		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		  }
	}

	@GetMapping("/getAllList")
	 
	public ResponseEntity<List<QRRequest>> GetAllList(  String key, String application, String auth_sign) {
		List<ApplicationName> AppList = AppName_repository.findByAppandCodeWithKey(application,auth_sign,key);
		 
		  if (AppList.isEmpty()) {
			  return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		  }
		  else {
		List<QRRequest> qrRequestList = docRe.findAll();

		if (qrRequestList.size() > 0) {

			return new ResponseEntity<>(qrRequestList, HttpStatus.OK);
		}

		else

		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		  }
	}

	@GetMapping("/getAllListCS")
	 
	public ResponseEntity<List<CustomerServices>> GetAllListofCS(String orderId, String key, String application, String auth_sign) {
		List<ApplicationName> AppList = AppName_repository.findByAppandCodeWithKey(application,auth_sign,key);
		 
		  if (AppList.isEmpty()) {
			  return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		  }
		  else {
		List<CustomerServices> qrRequestList = docCS.findByorderId(orderId);

		if (qrRequestList.size() > 0) {

			return new ResponseEntity<>(qrRequestList, HttpStatus.OK);
		}

		else

		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		  }
	}

	@GetMapping("/ConsumeQR")
	 
	public ResponseEntity<String> ConsumeQR(String orderId, String key, String application, String ServiceCode, String auth_sign) {
		List<ApplicationName> AppList = AppName_repository.findByAppandCodeWithKey(application,auth_sign,key);
		 
		  if (AppList.isEmpty()) {
			  return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		  }
		  else {
	//	CustomerServices qrCS;

	//	JsonValidator jsonCheck = new JsonValidator();
		
		//query.addCriteria(Criteria.where("orderID").is(orderId).andOperator(Criteria.where("code").is(ServiceCode)));
		 Criteria criteria = new Criteria();
	        criteria = criteria.and("orderID").is(orderId);
	        criteria = criteria.and("code").is(ServiceCode);
	        criteria = criteria.and("isConsumed").is("N");
	        Query query = new Query(criteria);    
	        
		Update update = new Update();
		update.set("isConsumed", "Y");

		
		  mt.findAndModify(query, update, CustomerServices.class);
		  System.out.println("Data Modified");
		 
		/*
		 * List<CustomerServices> qrRequestList =
		 * docCS.findByorderIdandCode(orderId,ServiceCode);
		 * 
		 * if (qrRequestList.size()>0) {
		 * 
		 * qrCS=qrRequestList.get(0);
		 * 
		 * Boolean isValid = true;
		 * 
		 * if (isValid==true) {
		 * 
		 * Query query = new Query();
		 * query.addCriteria(Criteria.where("orderID").is(orderId).andOperator(Criteria.
		 * where("code").is(ServiceCode))); Update update = new Update();
		 * update.set("age", 100);
		 * 
		 * docCS.updateFirst(query, update, CustomerServices.class);
		 * 
		 * // query.fields().include("name"); return new ResponseEntity<>("",
		 * HttpStatus.OK);
		 * 
		 * }
		 * 
		 * else { return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
		 * body("No Service to Consume!\n"); } }
		 * 
		 * else
		 * 
		 * { //return new ResponseEntity<>("Invalid", HttpStatus.NOT_FOUND); return
		 * ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
		 * body("Invalid QR Code!\n"); }
		 */
		return new ResponseEntity<>("",				  HttpStatus.OK);
		  }
	}
	@GetMapping("/CreateNewAPP")
	 
	public ResponseEntity<String> CreateNewAPP(String ApplicationName, String Auth_Sign) {
		
		String APIKey="QRGEN" + generateRandomPassword(19);
		  
		 
			 
				List<ApplicationName> AppList = AppName_repository.findByAppandCode(ApplicationName,Auth_Sign);
				  Boolean isValid = true;
				  if (AppList.isEmpty()) {
					  isValid =false;
					  ApplicationName AppName= new
								ApplicationName (ApplicationName ,APIKey,Auth_Sign,"Y");
						   
						    this.AppName_repository.save(AppName); 
						    
				 
							return new ResponseEntity<>(APIKey, HttpStatus.OK);
				  }
					  else
						  
					  {
							return new ResponseEntity<>("This APP and Prefix combination is already in use!", HttpStatus.CONFLICT);
						  
				  }
				  //ApplicationName qrCS=AppList.get(0);
					 
					 
		 
		
		 

	}
	
	
	@GetMapping("/getAPPList")
	 
	public ResponseEntity<List<ApplicationName>> getAPPList() {
		
		
		  
		 
			 
				List<ApplicationName> AppList = AppName_repository.findAll();
				return new ResponseEntity<>(AppList, HttpStatus.OK);
					 
		 
		
		 

	}
	public static String generateRandomPassword(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}
}
