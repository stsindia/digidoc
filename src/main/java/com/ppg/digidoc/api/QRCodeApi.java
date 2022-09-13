package com.ppg.digidoc.api;

import com.ppg.digidoc.models.CustomerServices;
import com.ppg.digidoc.models.QRRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(value = "QR Code API")
@RequestMapping(value = "/api")
public interface QRCodeApi {

    @ApiOperation(value = "QR Code Generation Service", nickname = "generateQRCode", notes = "QR Code Generation Service", response = String.class, tags={ "digidoc"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @GetMapping("/generateQRCode")
    public ResponseEntity<String> generateQRCode(QRRequest qrRequestParams);

    @ApiOperation(value = "QR Code Scanning Service", nickname = "scanQRCode", notes = "QR Code Scanning Service", response = String.class, tags={ "digidoc"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @GetMapping("/scanQRCode")
    public ResponseEntity<String> scanQRCode(String orderId, String key, String application, String ServiceCode, String auth_sign);

    @ApiOperation(value = "Get QR Request By Id", nickname = "getQRRequestByOrderId", notes = "Get QR Request By Id", response = String.class, tags={ "digidoc"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @GetMapping("/getQRRequestByOrderId")
    public ResponseEntity<QRRequest> getQRRequestByOrderId(String orderId, String key, String application, String auth_sign);

    @ApiOperation(value = "Get all QR Requests", nickname = "getQRRequests", notes = "Get all QR Requests", response = String.class, tags={ "digidoc"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @GetMapping("/getQRRequests")
    public ResponseEntity<List<QRRequest>> getQRRequests(String key, String application, String auth_sign);

    @ApiOperation(value = "Get all Consumer Services", nickname = "getConsumerServices", notes = "Get all Consumer Services", response = String.class, tags={ "digidoc"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @GetMapping("/getConsumerServices")
    public ResponseEntity<List<CustomerServices>> getConsumerServices(String orderId, String key, String application, String auth_sign);
}
