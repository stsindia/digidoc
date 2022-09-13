<html>
<head>
    <title>Welcome</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
        rel="stylesheet">
         <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
    <div id="header-top" class="header-top-content">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <a class="btn btn-success" href="/CreateNewQR">Create QR</a>
                    <a class="btn btn-success" href="/ViewQR">Scan and Get Info</a>
                    <a class="btn btn-success" href="/ViewList">View All Data</a>
                </div>
            </div>
        </div>
    </div>
    
    <div class="container">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Create QR Code</h3></div>
                                    <div class="card-body">
                                       
                                            <div class="row">
                                                <div class="col-md-6">
                                                	<div class="col-md-6">
                                                 		<label  >Enter Order No</label>
                                                 	</div>
                                                    <div class="col-md-6">
                                                        <input class="form-control" id="txtQrCode" type="text" value="STS" >
                                                        
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                	<div class="col-md-6">
                                                 		<label  >Application Name</label>
                                                 	</div>
                                                    <div class="col-md-6">
                                                        <input class="form-control" id="txtapplication" type="text" value="STS" >
                                                        
                                                    </div>
                                                </div>
                                                
                                            </div> </br>
                                            
                                            <div class="row">
                                                <div class="col-md-6">
                                                	<div class="col-md-6">
                                                 		<label >Auth Key</label>
                                                 	</div>
                                                    <div class="col-md-6">
                                                        <input class="form-control" id="txtkey" type="text" value="XYDF45KLJ^%&*99">
                                                        
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                	<div class="col-md-6">
                                                 		<label  >Eye Color</label>
                                                 	</div>
                                                    <div class="col-md-6">
                                                        <input class="form-control" id="txteyecolor" type="text" value="#2A42AC" >
                                                        
                                                    </div>
                                                </div>
                                                
                                            </div></br>
                                              <div class="row">
                                                <div class="col-md-6">
                                                	<div class="col-md-6">
                                                 		<label >Background Color</label>
                                                 	</div>
                                                    <div class="col-md-6">
                                                        <input class="form-control" id="txtbackground_color" type="text" value="#FFFFFF">
                                                        
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                	<div class="col-md-6">
                                                 		<label  >Data Gradient Color</label>
                                                 	</div>
                                                    <div class="col-md-6">
                                                        <input class="form-control" id="txtdata_gradient" type="text" value="#2A42AC" >
                                                        
                                                    </div>
                                                </div>
                                                
                                            </div></br>
                                             
                                            <div class="row mb-3">
                                                 
                                                 
                                            </div>
                                            <div class="mt-4 mb-0">
                                                <div class="d-grid"><a class="btn btn-primary btn-block" href="#"  id="submit_btn">Create QR Code</a></div>
                                            </div>
                                       
                                    </div>
                                    
                                </div>
                                 </br>
                                              <div class="row">
                                              <div class="row mb-3">
                                               <img id="QRImage" style='height:200px;width:200px;'/>
                                              
                                              
                                              </div>
                                              </div>
                                               </br>
                                              <div class="row">
                                              <div style='height:200px;width:200px;' id="div_imagetranscrits">
                                                
                                              
                                              
                                              </div>
                                              </div>
  
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function()
     {
         var txtQrCode = $('#txtQrCode').val();  var key= $('#txtkey').val(); var application= $('#txtapplication').val(); var centerLogo= "";
	  var urlContentView= ""; var contentType= ""; var content= "";
	  
	  var settings = {
  "url": "http://localhost:8181/api/generateQRCode?application=POSTMAN&key=nokeydef&content=&orderId=Tec244",
  "method": "GET",
  "timeout": 0,
};
 
$.ajax(settings).done(function (response) {
  console.log(response);
   console.log(_arrayBufferToBase64(response));
    $('.div_imagetranscrits').html('<img src="data:image/png;base64,' + _arrayBufferToBase64(response) + '" />');
});
	  
	  
	   
     function _arrayBufferToBase64(buffer) {
    var binary = '';
    var bytes = new Uint8Array(buffer);
    var len = bytes.byteLength;
    for (var i = 0; i < len; i++) {
        binary += String.fromCharCode(bytes[i]);
    }
    return window.btoa(binary);
};
     
     $('#submit_btn').click(function(){
    var txtQrCode = $('#txtQrCode').val();  var key= $('#txtkey').val(); var application= $('#txtapplication').val(); var centerLogo= "";
	  var urlContentView= ""; var contentType= ""; var content= "";
	  
	  var settings = {
  "url": "http://localhost:8181/generate?application=POSTMAN&key=nokeydef&content=&orderId=Tec244",
  "method": "GET",
  "timeout": 0,
};
 
$.ajax(settings).done(function (response) {
  //console.log(response);
   //console.log(_arrayBufferToBase64(response));
   var bytes = new Uint8Array(response); // pass your byte response to this constructor

var blob=new Blob([bytes], {type: "image/png"});// change resultByte to bytes

var link=document.createElement('a');
link.href=window.URL.createObjectURL(blob);
link.download="myFileName.png";
link.click();
    var imageBlob = new Blob([response], {type:'image/png'});
    var str = btoa(String.fromCharCode.apply(null, new Uint8Array(response)));

  console.log(str);
    let img = new Image()
  img.src = URL.createObjectURL(imageBlob)
  var url = window.URL || window.webkitURL;
            var src = url.createObjectURL(imageBlob);
           //  var imageUrl = urlCreator.createObjectURL(imageBlob);
               document.querySelector("#QRImage").src = "data:image/png;base64," + btoa(unescape(encodeURIComponent(response)));
           // $('#QRImage').attr("src", src);
            $('.div_imagetranscrits').html('<img src="data:image/png;base64,' + response + '" />');
   // $('.div_imagetranscrits').html('<img src="data:image/png;base64,' + _arrayBufferToBase64(response) + '" />');
});
	  
	  
	   


	 /* 
		var JSONObject=  {"orderId":txtQrCode,"key":key, "application":application , "centerLogo":centerLogo , "urlContentView":urlContentView , "contentType":contentType,"content":content } ;
		//alert(JSONObject);
		//var jsonData = JSON.parse( JSONObject );    
    $.ajax({
        type: "GET",
        dataType : "jsonp",
        url : "http://localhost:8181/generate/",
        data: JSONObject,
         crossDomain: true,
           headers: {
               'Content-Length' :  '28211' },
        success : function(data) {
           alert(data);
            var parsed_data = JSON.parse(data);
            alert(parsed_data);
          //  document.getElementById("QRImage").src = parsed_data;
            console.log(parsed_data.success);
        },
		error: function (jqXHR) {
		   // alert('error; ' + error);
		  //  document.getElementById("QRImage").src = error;
		    var msg = '';
	        if (jqXHR.status === 0) {
	            msg = 'Not connect.\n Verify Network.';
	        } else if (jqXHR.status == 404) {
	            msg = 'Requested page not found. [404]';
	        } else if (jqXHR.status == 500) {
	            msg = 'Internal Server Error [500].';
	         
	        } else {
	            msg = 'Uncaught Error.\n' + jqXHR.responseText;
	        }
	        $('.div_imagetranscrits').html('<img src="data:image/png;base64,' + jqXHR.responseText + '" />');
	     document.getElementById("QRImage").src = jqXHR.responseText;
	     console.log(jqXHR.responseText);
		}
    });
     */
    });
  
    });



</script>
</body>
</html>