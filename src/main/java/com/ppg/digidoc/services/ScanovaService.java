package com.ppg.digidoc.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.ppg.digidoc.models.QRRequest;
import lombok.extern.java.Log;

@Service
@Log
public class ScanovaService {
	 
    private final RestTemplate restTemplate;
    private final ApiConfig apiConfig;

    public ScanovaService(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
        this.restTemplate = new RestTemplate();
    }
    /* added by sts - 2022-08-02
     * 
     * Key Param used of Scanova API :
     * 	data : the text we want to encode in the QR Code *string 
     * 	size :values : s (300px), m (450px), l (600px), xl (900px), xxl (1,200px), and xxxl (1,500px) *string 
     * 			we can also define custome size by another param
     *  error_correction : Error Correction helps maintain scannability of the QR Code even if there is some damage to data modules. 
     *  					Error correction is measured in damage in percentage (%) that it can sustain. Values are L (~7%), M (~15%), Q (~25%), and H (~30%).
     *  logo.url		 :  Enter the URL of the image (PNG only) Tip: Ensure image source allows cross-domain access.
     *  logo.size		: 	Enter the size of the logo image as a percentage (%) of size of logo. 
     *  					Tip: High logo size may make the QR Code unscannable. (number)
     *  logo.excavated    :	Parameter valid only if data in logo.url is specified. Use this paramater to specify if the blocks around the logo should be remove (true) 
     *  					or should not be removed (false) Tip: If selecting true, set error correction to a higher-level to ensure loss of blocks doesn't affect scannability
     *  
     *  format : 			png/jpg/svg supported
     * 
     * 
     * 
     */
    public byte[] getQRCode(QRRequest qrCodeParam) {
    	String qrCodeText=qrCodeParam.getId();
        Map<String, String> params = new HashMap<String, String>();
        params.put("data", qrCodeText);
          String eyeColor=qrCodeParam.getEyeColor();
          String backgroundColor=qrCodeParam.getBackgroundColor();
          String dataGradientColor=qrCodeParam.getDataGradientColor();
        
          if (eyeColor == null || eyeColor.isEmpty() || eyeColor.trim().isEmpty())  {eyeColor ="black";} else {eyeColor ="" + eyeColor ;}
          if (backgroundColor == null || backgroundColor.isEmpty() || backgroundColor.trim().isEmpty())  {backgroundColor ="white";}else {backgroundColor ="" + backgroundColor ;}
          if (dataGradientColor == null || dataGradientColor.isEmpty() || dataGradientColor.trim().isEmpty())  {dataGradientColor ="black";}else {dataGradientColor ="" + dataGradientColor ;}
          
      //    ColorUtils colorUt=new ColorUtils();
         
          
			/*
			 * if (eyeColor == null || eyeColor.isEmpty() || eyeColor.trim().isEmpty())
			 * {eyeColor ="%23000000";} else {eyeColor ="%23" + eyeColor ;} if
			 * (backgroundColor == null || backgroundColor.isEmpty() ||
			 * backgroundColor.trim().isEmpty()) {backgroundColor ="%23FFFFFF";}else
			 * {backgroundColor ="%23" + backgroundColor ;} if (dataGradientColor == null ||
			 * dataGradientColor.isEmpty() || dataGradientColor.trim().isEmpty())
			 * {dataGradientColor ="%23000000";}else {dataGradientColor ="%23" +
			 * dataGradientColor ;}
			 */
          
          
          
			/*
			 * try {
			 * 
			 * URL url = new URL(apiConfig.getTextQrCodeUrl()); HttpURLConnection conn =
			 * (HttpURLConnection) url.openConnection(); conn.setRequestMethod("GET");
			 * conn.setRequestProperty("Accept", "image/png"); conn.setDoOutput(true);
			 * DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			 * dos.writeChars("data=" +qrCodeText + "&logo.url=" +apiConfig.getlogo_url()
			 * +"&eye_color_inner=" + eyeColor + "&eye_color_outer=" + eyeColor +
			 * "&eye_pattern=RECT_RECT"); dos.flush(); dos.close();
			 * 
			 * 
			 * if (conn.getResponseCode() != 200) { throw new
			 * RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()); }
			 * 
			 * BufferedReader br = new BufferedReader(new InputStreamReader(
			 * (conn.getInputStream())));
			 * 
			 * String output; System.out.println("Output from Server .... \n"); while
			 * ((output = br.readLine()) != null) { System.out.println(output); }
			 * 
			 * conn.disconnect();
			 * 
			 * } catch (MalformedURLException e) {
			 * 
			 * e.printStackTrace();
			 * 
			 * } catch (IOException e) {
			 * 
			 * e.printStackTrace();
			 * 
			 * }
			 */
          HttpHeaders headers = new HttpHeaders();
          headers.setAccept(Arrays.asList(MediaType.IMAGE_PNG));
          HttpEntity <String> entity = new HttpEntity<String>(headers);
          
        String apiUrl = UriComponentsBuilder.fromUriString(apiConfig.getTextQrCodeUrl())
                .queryParam("data", qrCodeText)
        		 .queryParam("logo.url", apiConfig.getlogo_url())
        		 .queryParam("logo.size", apiConfig.getlogo_size())
        		 .queryParam("logo.excavated", apiConfig.getlogo_excavated())  //set error correction to a higher-level to ensure loss of blocks doesn't affect scannability
        		.queryParam("error_correction", apiConfig.geterror_correction())
        		 .queryParam("eye_color_inner", eyeColor)
        		 .queryParam("eye_color_outer", eyeColor)
        		 .queryParam("eye_pattern", "RECT_RECT")
        		 
        		 .queryParam("background_color", backgroundColor)
        		 .queryParam("data_gradient_start_color", dataGradientColor)
        		 .queryParam("data_gradient_end_color", dataGradientColor)
        		 .queryParam("apikey", "gxktljtpdoqrbnboprhojsvdwguyloewxyxhgplg")
        		 .queryParam("format", "png")
                .build().toString();
       
       //  apiUrl="https://api.scanova.io/v2/qrcode/text?data=ssssss&size=m&error_correction=M&data_pattern=RECT&eye_pattern=RECT_RECT&data_gradient_style=None&data_gradient_start_color=%23330000&data_gradient_end_color=%23330000&eye_color_inner=%23330000&eye_color_outer=%23330000&background_color=%23FFFFFF&logo.url=https%3A%2F%2Fwww.plazapremiumlounge.com%2FApp_Themes%2FPPL%2Ffavicon-32.png&logo.size=15&logo.excavated=true&logo.angle=0&poster.left=50&poster.top=50&poster.size=40&poster.eyeshape=ROUND_RECT&poster.dataPattern=ROUND&format=png";
        log.info("URL For text = " + apiUrl);
        
        ResponseEntity<byte[]> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity,
                byte[].class);
 
		/*
		 * ResponseEntity<byte[]> response = restTemplate.exchange(apiUrl,
		 * HttpMethod.GET, new HttpEntity<String>(""), byte[].class);
		 */
        
        return response.getBody();

    }

}
