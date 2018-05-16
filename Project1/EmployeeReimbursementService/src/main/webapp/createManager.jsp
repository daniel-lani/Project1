<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../ReimbursementCss/bootstrap.css/">
</head>
<body>
			<%@ page import="com.revature.employee.FinanceManager" %>
			<%FinanceManager man = (FinanceManager) request.getSession().getAttribute("authorizedUser"); %>

            <div class="container">
                <div class="jumbotron">
                    <h1 class="display-4">Update your info</h1>
                    <hr class="my-4">
                </div>
            </div>
				<div class="container">
		<div class="col-md-6 col-offset-2">
			<form action="updateManager.do" method="post">
			<div class = "form-group well">
				<div class="form-group">
					<label for="firstname"><strong>FirstName</strong></label>
					<input type="text" name="firstname" id="firstname" class="form-control" placeholder=<%= man.getFirstName() %>>
				</div>
				<div class="form-group">
				 	<label for="lastname"><strong>LastName</strong></label>
					<input type="text" name="lastname" id="lastname" class="form-control" placeholder=<%= man.getLastName() %>>
				</div>
				<div class="form-group">
					<label for="email"><strong>Email</strong></label>
					<input type="text" name="email" id="email" class="form-control" placeholder=<%= man.getEmail() %>>
				</div>
				<div class="form-group">
					<label for="address"><strong>Address</strong></label>
					<input type="text" name="address" id="address" class="form-control" placeholder=<%= man.getAddress() %>>
				</div>
				
				<div class="button-group">
					<input type="submit" class="btn btn-success" id ="manSubmit" value="Submit">
					<input type="reset" class="btn btn-danger" value="Reset">
				</div>
				</div>
			</form>
	</div>
	<script type="text/javascript">
	 window.onload = function(){
	    document.getElementById("manSubmit")
	            .addEventListener("click",updateValues);
	  }
function updateValues()
      {
        xmlhttp.onreadystatechange=function(){}
        xmlhttp.open("post", "createManager.jsp", true);
        xmlhttp.send("/managerRefresh.do");
}
</script>        
                        <script src = "./userCreation.js"></script> 
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>