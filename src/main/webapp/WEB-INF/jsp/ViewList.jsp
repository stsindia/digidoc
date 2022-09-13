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
                                                        <input class="form-control" id="txtQrCode" type="text" value="STS"  >
                                                        
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
                                                        <div class="d-grid"><a class="btn btn-primary btn-block" href="#"  id="submit_btn">Get QR Code</a></div>
                                                        
                                                    </div>
                                                </div>
                                                
                                            </div> 
                                             
                                            
                                        
                                    </div>
                                    
                                </div>
                                  
    <div class="container">
        <table class="table table-striped" id="tblList">
            <caption>List of QR Code</caption>
            
            
            <thead>
                <tr>
                    <th>Order</th>
                    <th>Key</th>
                    <th>Application</th>
                    <th>Content</th>
                    <th>View</th>
                </tr>
            </thead>
            <tbody>
                   
            </tbody>
        </table>
        
       
    </div>
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function()
     {
     $('#submit_btn').click(function(){
    var txtQrCode = $('#txtQrCode').val();  var key= $('#txtkey').val(); var application= $('#txtapplication').val(); var centerLogo= "";
	  var urlContentView= ""; var contentType= ""; var content= "";
	  
	  	  $("#tblList  tr").remove();
	  var settings = {
		  "url": "http://localhost:8181/api/getQRRequests?orderId=STS06&application=POSTMAN&key=nokeydef",
		  "method": "GET",
		  "timeout": 0,
		};
		
		$.ajax(settings).done(function (getRowData) {
		 //  var getRowData = getRowData.Data;
		  //var parsed_data = JSON.parse(getRowData);
		// console.log(response);
		    for (var i = 0; i < getRowData.length; i++) {
		    var strRow="<tr><td>" + getRowData[i].orderId + " </td><td>" + getRowData[i].key + "</td><td>" + getRowData[i].application + "</td><td>" + getRowData[i].content + "</td><td><a class='btn btn-warning' href='#'>View</a></td></tr>";
     			$('#tblList> tbody:last-child').append(strRow);
   		  }
    
		});
	  
		var JSONObject=  {"orderId":txtQrCode,"key":key, "application":application  } ;
		//alert(JSONObject);
		//var jsonData = JSON.parse( JSONObject );    
   
    });
    });



</script>
</body>
</html>