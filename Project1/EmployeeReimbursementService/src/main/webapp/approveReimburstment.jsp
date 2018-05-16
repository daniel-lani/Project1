<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>submitReimburstment</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h1 class="display-4">approve a Reimburstment</h1>
			<hr class="my-4">
		</div>
	</div>

	<div class="container">
		<nav class="navbar navbar-inverse">
			<div class="navbar-header col-md-2">
				<a href="./managerHome.jsp" class="navbar-brand">Home</a>
			</div>
			<ul class="navbar-nav nav col-md-6">
				<li><a href="./managerInfo.jsp">Info</a></li>
				<li><a href="./viewManagerReimburstment.jsp">View</a></li>
				<li><a href="./submitManagerReimburstment.jsp">Submit</a></li>
				<li class="active"><a href="./approveReimburstment.jsp">Approve</a>></li>
			</ul>
			<ul class="navbar-nav nav navbar-right col-md-2 offset-md-2">
				<li><a href="logout.do">Log out <span
						class="glyphicon glyphicon-log-out"></span></a></li>
			</ul>

		</nav>
	</div>
	<script type="text/javascript">
window.onload = function(){
        populateReburTable();
    }

    function populateReburTable(){
        //Step 1 Create an xmlhttprequest object

        let xhr = new XMLHttpRequest();

        //Step 2 Add a callback function to on ready state change

        xhr.onreadystatechange = function(){
            //Step 5 handle the response
            if(xhr.readyState == 4 && xhr.status == 200){
            	
                var reburObj = <%= request.getSession().getAttribute("pendingReimbursements") %>;
                for(let rebur of reburObj){
                    //get the properties of the JSON element
                    let cat = rebur.cat;
                    let subId = rebur.sumbitterId;
    				let id= rebur.reimburseId;
                    let timeSub = rebur.timeSubmitted;
                    let amount = rebur.amount;
                    

 
                    //Dynamically create the HTML tags
                    let row = document.createElement("tr");
                    let tdId = document.createElement("td");
                    let tdCat = document.createElement("td");
                    let tdAmount = document.createElement("td");
                    let tdSubId = document.createElement("td");
                    let tdDateSub = document.createElement("td");
                    


                    
					                    
                    tdId.textContent = id;
                    tdCat.textContent = cat;
                    tdAmount.textContent = amount;
                    tdSubId.textContent = subId;
                    tdDateSub.textContent = timeSub;
                   
                    //Programatically bootstrapify each tdCompleted

                    row.appendChild(tdId);
                    row.appendChild(tdCat);
                    row.appendChild(tdAmount);
                    row.appendChild(tdSubId);
                    row.appendChild(tdDateSub);

                    //Append the row

                    document.getElementById("PendingReimbursments").appendChild(row);
                }
            }
        }
        //Step 3 call the open method 
        xhr.open("GET","/EmployeeReimbursementService/approveReimburstment.jsp",true);

        //Step 4 call the send method

        xhr.send();
    }
    </script>
	<div class="container">
		<div class="page-header">
			<h2>Pending Reimbursements</h2>
		</div>
		<table class="table table-responsive table-stripped">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Category</th>
					<th>Amount</th>
					<th>Submitting Employee</th>
					<th>Date Submitted</th>
				</tr>
			</thead>
			<tbody id="PendingReimbursments"></tbody>
		</table>
	</div>
	<div class="container">
		<div class="col-md-6 col-offset-3">
			<form action="approve.do" method="post">
				<div class="form-group">
					<input type="text" name="id" class="form-control" required
						placeholder="Enter the id you wish to approve">
				</div>
				<div class="form-group">
					<input type="radio" name="contact"value="approve"> 
					<label for="contactChoice1">Approve</label> 
					<input type="radio" name="contact"value="deny"> 
					<label for="contactChoice1">Deny</label>
				</div>
		</div>
		<div class="button-group">
			<input type="submit" class="btn btn-success" value="Submit">
		</div>
		</form>
	</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>