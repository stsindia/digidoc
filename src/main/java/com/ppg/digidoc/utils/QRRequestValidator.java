package com.ppg.digidoc.utils;

import com.ppg.digidoc.eh.exception.ValidationException;
import com.ppg.digidoc.models.ApplicationName;
import com.ppg.digidoc.models.QRRequest;
import com.ppg.digidoc.repository.ClientApplicationRepository;
import com.ppg.digidoc.repository.MetaDocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QRRequestValidator {

    @Autowired
    private ClientApplicationRepository applicationNameRepo;

    @Autowired
    private MetaDocRepository docRe;

    public boolean validateQRRequest(QRRequest qrRequest) {
        if (qrRequest == null) {
            throw new ValidationException("Request parameters are not available!");
        }
        String orderId = qrRequest.getorderId();
        String key = qrRequest.getkey();
        String application = qrRequest.getApplication();
        String auth_sign = qrRequest.getauth_sign();
        try {

            //Step 1: Required parameters are present in the request
            if ((orderId == null) || (key == null) || (application == null) || (auth_sign == null)) {
                throw new ValidationException("OrderID/APPKey/Application/auth_sign can not be blank!");
            }

            //Step 2: Check if valid Application name provided
        } catch (Exception e) {
            throw new ValidationException("OrderID/APPKey/Application/auth_sign can not be blank!");
        }


        List<ApplicationName> apps = applicationNameRepo.findByAppandCodeWithKey(application, auth_sign, key);
        Boolean isValid = true;
        if (apps.isEmpty()) {
            throw new ValidationException("Invalid credentials!");
        }

        List<QRRequest> qrRequestList = docRe.findByOrderIDAndApp(orderId, application);

        if (!qrRequestList.isEmpty()) {
            throw new ValidationException("This OrderID/JobID is already issued for given Application! Please Change the combination");
        }

        return true;
    }
}
