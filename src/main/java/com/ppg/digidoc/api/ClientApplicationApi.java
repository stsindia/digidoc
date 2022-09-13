package com.ppg.digidoc.api;

import com.ppg.digidoc.models.ApplicationName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(value = "Digidoc Generic API")
@RequestMapping(value = "/api")
public interface ClientApplicationApi {

    @ApiOperation(value = "Generate API Key for new application", nickname = "generateAPIKey", notes = "Generate API Key for new application", response = String.class, tags={ "digidoc"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @GetMapping("/generateAPIKey")
    public ResponseEntity<String> generateAPIKey(String ApplicationName, String Auth_Sign);

    @ApiOperation(value = "Get All Client Applications", nickname = "getClients", notes = "Get All Client Applications", tags={ "digidoc"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @GetMapping("/getApplications")
    public ResponseEntity<List<ApplicationName>> getClientApplications();

}
