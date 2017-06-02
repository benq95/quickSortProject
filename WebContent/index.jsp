<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>QucikSort</title>
</head>
<body>
	
	<div class="jumbotron text-center">
  <h1>QuickSort Application</h1>
  <p>Insert your data below(one element in one line) and click "SORT" to sort it!</p> 
</div>
		
	<div class="row">
			<div class="box">
				<div class="col-xs-6 col-sm-4"></div>
				<div class="col-xs-6 col-sm-4">
					<form action="<c:url value="/webservice/rest" />" id="loginForm" name="loginForm" method="post">
						<textarea class="form-control" rows="20" id="comment"></textarea>
						
						What type of data do you want to sort?:
						<div class="radio">
							<label><input type="radio" name="optradio" class="radio" checked="checked" value="1">Integer</label>
						</div>
						<div class="radio">
							<label><input type="radio" name="optradio" id="radio" value="2">String</label>
						</div>
						<button type="submit" value=" Send" class="btn btn-primary btn-lg btn-block" id="loginButton">SORT</button>
					</form>	
				</div>
				<div class="col-xs-6 col-sm-4"></div>
			</div>
	</div>
	
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
  	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  	<script type="text/javascript">
  	$(document).ready(function(){
  		$("#loginForm").submit(function(e) {
  			var postData = $(".form-control").val().split("\n"); //get textarea data
  		    var formURL = $(this).attr("action"); //get form url from form
  		    if($('.radio:checked').val()=="1"){
				var isInteger = true;
  	  	  	} else {
				var isInteger = false;
    	  	}
  		    
  		    var data = { //create json
					"data" : postData,
					"isInteger" : isInteger
  		  	}
  		    var requestData = JSON.stringify(data); //parse json
  			$.ajax({
  				type : "POST",
  				url : formURL,
  				dataType : "json",
  				contentType: "application/json; charset=utf-8",
  				data : requestData,
  				success : function(ret) {
  	  				//fill texarea with response array
  					$(".form-control").val("");
  					for(i = 0; i < ret.data.length; i++){
  						$(".form-control").val($(".form-control").val() + ret.data[i] + "\n");
  	  				}
  				},
  				complete : function() {
  				},
  				error : function(xhr, errorText, errorThrown) {
  	  				//alert user if any error occured
  	  				if(xhr.status==400){
						alert("Invalid input data! Check your input or set data type to String.");
					} else {
						alert("Internal server error. Please try again later.");
					}
  				}
  			})
  			//stop form action
  			e.preventDefault();
  		})
  	})
  	</script>
</body>
</html>