<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>submitReimburstment</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../ReimbursementCss/bootstrap.css/">
</head>
<body>
            <div class="container">
                <div class="jumbotron">
                    <h1 class="display-4">view employee reimburstments</h1>
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
                                <li class="active"><a href="./viewManagerReimburstment.jsp">View</a></li>
                                <li><a href="./submitManagerReimburstment.jsp">Submit</a></li>
                                <li><a href="./approveReimburstment.jsp">Approve</a>></li>
                            </ul>
                            <ul class="navbar-nav nav navbar-right col-md-2 offset-md-2">
                                <li><a href="logout.do">Log out <span class="glyphicon glyphicon-log-out"></span></a></li> 
                            </ul>
                        
                    </nav>
                </div>
                <div class="container">
		<div class="col-md-6 col-offset-3">
			<form action="newPending.do" method="post">
				<div class="form-group">
					<input type="text" name="id" class="form-control" required
						placeholder="User Id">
				</div>
				<div class="button-group">
					<input type="submit" class="btn btn-success" value="Submit">
				</div>
			</form>
		</div>
	</div>
                <div class="container">
                <div class="page-header">
                    <h2>Employee Reimbursments</h2>
                </div>
                <table class="table table-responsive table-stripped">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Category</th>
                            <th>Amount</th>
                            <th>Date Submitted</th>
                            <th>Submitting Employee</th>
                            <th>Status</th>
                            <th>Date Approved </th>
                            <th>Approving Manager</th>
                            
                        </tr>
                    </thead>
                    <tbody id = "ManagerReimbursments"></tbody>
                </table>
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
            	
                var reburObj = <%= request.getSession().getAttribute("reimbursements") %>;
                for(let rebur of reburObj){
                    //get the properties of the JSON element
                    let cat = rebur.cat;
                    let appId = rebur.approverId;
                    let subId = rebur.sumbitterId;
    				let id= rebur.reimburseId;
                    let timeApp =(typeof rebur.timeApproved === 'undefined') ? 'pending' : rebur.timeApproved;
                    let timeSub = rebur.timeSubmitted;
                    let amount = rebur.amount;
                    let approved = (rebur.approved === 'true') ? "Yes" : "No";
 
                    //Dynamically create the HTML tags
                    let row = document.createElement("tr");
                    let tdId = document.createElement("td");
                    let tdCat = document.createElement("td");
                    let tdAmount = document.createElement("td");
                    let tdAppId = document.createElement("td");
                    let tdSubId = document.createElement("td");
                    let tdDateSub = document.createElement("td");
                    let tdDateApp = document.createElement("td");
                    let tdStatus = document.createElement("td");

                    
					                    
                    tdId.textContent = id;
                    tdCat.textContent = cat;
                    tdAmount.textContent = amount;
                    tdDateSub.textContent = timeSub;
                    tdAppId.textContent = appId;
                    tdSubId.textContent = subId;
                    tdDateApp.textContent = timeApp;
                    tdStatus.textContent = approved;

                   
                    //Programatically bootstrapify each tdCompleted


                    row.appendChild(tdId);
                    row.appendChild(tdCat);
                    row.appendChild(tdAmount);
                    row.appendChild(tdDateSub);
                    row.appendChild(tdSubId);
                    row.appendChild(tdStatus);
                    row.appendChild(tdDateApp);
					row.appendChild(tdAppId);
                   
                    //Append the row

                    document.getElementById("ManagerReimbursments").appendChild(row);
                }
            }
        }
        //Step 3 call the open method 
        xhr.open("GET","/EmployeeReimbursementService/viewManagerReimburstment.jsp",true);

        //Step 4 call the send method

        xhr.send();
    }
    </script>
                
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>