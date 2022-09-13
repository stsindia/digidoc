package com.ppg.digidoc.api;

import java.util.List;

import com.ppg.digidoc.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ppg.digidoc.models.ApplicationName;
import com.ppg.digidoc.repository.ClientApplicationRepository;
import com.ppg.digidoc.repository.MetaDocRepository;

import lombok.extern.slf4j.Slf4j;

@EnableMongoRepositories
@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class ClientApplicationApiController implements ClientApplicationApi {

    @Autowired
    private MetaDocRepository docRe;

    @Autowired
    private ClientApplicationRepository clientApplicationRepository;

    public ResponseEntity<String> generateAPIKey(String ApplicationName, String Auth_Sign) {

        String APIKey = "QRGEN" + CommonUtils.generateRandomPassword(19);

        List<ApplicationName> AppList = clientApplicationRepository.findByAppandCode(ApplicationName, Auth_Sign);
        Boolean isValid = true;
        if (AppList.isEmpty()) {
            isValid = false;
            ApplicationName AppName = new
                    ApplicationName(ApplicationName, APIKey, Auth_Sign, "Y");

            this.clientApplicationRepository.save(AppName);


            return new ResponseEntity<>(APIKey, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("This APP and Prefix combination is already in use!", HttpStatus.CONFLICT);

        }

    }

    public ResponseEntity<List<ApplicationName>> getClientApplications() {
        List<ApplicationName> AppList = clientApplicationRepository.findAll();
        return new ResponseEntity<>(AppList, HttpStatus.OK);
    }

}
