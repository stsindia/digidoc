package com.ppg.digidoc.utils;

import com.ppg.digidoc.models.CustomerServices;
import com.ppg.digidoc.models.QRRequest;
import com.ppg.digidoc.models.QRResponse;
import com.ppg.digidoc.repository.CustomerServicesRepo;
import com.ppg.digidoc.repository.MetaDocRepository;
import com.ppg.digidoc.services.ScanovaService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
@Slf4j
public class QRCodeGenerator {
    private final MetaDocRepository repository;
    private final CustomerServicesRepo CS_repository;
    private final ScanovaService scanovaService;

    @Autowired
    public QRCodeGenerator(ScanovaService scanovaService, MetaDocRepository repository, CustomerServicesRepo CS_repository) {
        this.scanovaService = scanovaService;
        this.repository = repository;
        this.CS_repository=CS_repository;
    }

    public QRResponse generate(QRRequest qrCodeParam) {
        QRResponse qrResponse = new QRResponse();
        // save the transaction and get a qr response object
        
   	 JsonValidator jsonCheck = new JsonValidator();
   	 
   	 
        
        var saved = this.repository.save(qrCodeParam);
        
  	  String content=saved.getcontent(); 
	   Boolean isValid = jsonCheck.isValidJson(content);
	  
	   if (isValid ==true)
	   {
		   JSONArray  jList=new JSONArray (jsonCheck.rtnJSONArray(content) );
		   for (int i = 0; i < jList.length(); i++) {
			   JSONObject objectInArray = jList.getJSONObject(i);
			  // String[] elementNames = JSONObject.getNames(objectInArray);
			   
			    CustomerServices cs = new
			    		CustomerServices(qrCodeParam.getorderId() ,objectInArray.getString("code"),objectInArray.getString("isConsumed"), objectInArray.getString("Remark"));
			   
			    this.CS_repository.save(cs); 
			    
			    
				/*
				 * for (String elementName : elementNames) { String value =
				 * objectInArray.getString(elementName);
				 * 
				 * }
				 */
			}
		   
	   }

	  
        // for now return our order id instead of auto unique id to easy test and read
        
        //qrResponse.setQrImage(this.scanovaService.getQRCode(saved.getId()));
        qrResponse.setQrImage(this.scanovaService.getQRCode(saved));
        
        try {
			writeBytesToFileNio("c:/temp/qrPics/" + saved.getId() + ".png", qrResponse );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         //qrResponse.setQrText(saved.getId());
        return qrResponse;
    }
    
    private static void writeBytesToFileNio(String fileOutput, QRResponse qr)
            throws IOException {
    	byte[] bytes=qr.getQrImage();
            Path path = Paths.get(fileOutput);
            Files.write(path, bytes);

        }
    public Optional<QRRequest> getQRContent(String qrContent) {
        return this.repository.findById(qrContent);
    }
}
