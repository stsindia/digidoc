package com.ppg.digidoc.api;

import com.ppg.digidoc.models.ApplicationName;
import com.ppg.digidoc.models.CustomerServices;
import com.ppg.digidoc.models.QRRequest;
import com.ppg.digidoc.repository.ClientApplicationRepository;
import com.ppg.digidoc.repository.CustomerServicesRepo;
import com.ppg.digidoc.repository.MetaDocRepository;
import com.ppg.digidoc.utils.QRCodeGenerator;
import com.ppg.digidoc.utils.QRRequestValidator;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;

@EnableMongoRepositories
@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class QRCodeApiController implements QRCodeApi {

    @Autowired
    private MongoTemplate mt;
    private QRCodeGenerator qrCodeGenerator;
    @Autowired
    private MetaDocRepository docRe;
    @Autowired
    private CustomerServicesRepo docCS;

    @Autowired
    private ClientApplicationRepository clientApplicationRepository;

    @Autowired
    private QRRequestValidator requestValidator;

    @Autowired

    public QRCodeApiController(QRCodeGenerator qrCodeGenerator) {
        this.qrCodeGenerator = qrCodeGenerator;
    }

    /**
     * Generate the QR Code
     *
     * @param qrRequestParams
     * @return the encoded qr code
     */
    public ResponseEntity<String> generateQRCode(QRRequest qrRequestParams) {
        log.info(qrRequestParams.toString());

        if (requestValidator.validateQRRequest(qrRequestParams)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            String encodeToString = Base64.getEncoder().encodeToString(this.qrCodeGenerator.generate(qrRequestParams).getQrImage());

            return ResponseEntity.ok().headers(headers).body(encodeToString);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<String> scanQRCode(String orderId, String key, String application, String ServiceCode, String auth_sign) {
        List<ApplicationName> AppList = clientApplicationRepository.findByAppandCodeWithKey(application, auth_sign, key);

        if (AppList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            Criteria criteria = new Criteria();
            criteria = criteria.and("orderID").is(orderId);
            criteria = criteria.and("code").is(ServiceCode);
            criteria = criteria.and("isConsumed").is("N");
            Query query = new Query(criteria);

            Update update = new Update();
            update.set("isConsumed", "Y");

            mt.findAndModify(query, update, CustomerServices.class);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    public ResponseEntity<QRRequest> getQRRequestByOrderId(String orderId, String key, String application, String auth_sign) {
        List<ApplicationName> AppList = clientApplicationRepository.findByAppandCodeWithKey(application, auth_sign, key);

        if (AppList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {

            QRRequest qrRequest;
            List<QRRequest> qrRequestList = docRe.findByUId(orderId);

            if (qrRequestList.size() > 0) {

                qrRequest = qrRequestList.get(0);
                return new ResponseEntity<>(qrRequest, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }

    public ResponseEntity<List<QRRequest>> getQRRequests(String key, String application, String auth_sign) {
        List<ApplicationName> AppList = clientApplicationRepository.findByAppandCodeWithKey(application, auth_sign, key);

        if (AppList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            List<QRRequest> qrRequestList = docRe.findAll();

            if (qrRequestList.size() > 0) {

                return new ResponseEntity<>(qrRequestList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }

    public ResponseEntity<List<CustomerServices>> getConsumerServices(String orderId, String key, String application, String auth_sign) {
        List<ApplicationName> AppList = clientApplicationRepository.findByAppandCodeWithKey(application, auth_sign, key);

        if (AppList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            List<CustomerServices> qrRequestList = docCS.findByorderId(orderId);

            if (qrRequestList.size() > 0) {

                return new ResponseEntity<>(qrRequestList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }

}
