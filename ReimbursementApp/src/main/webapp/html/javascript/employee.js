
window.onload = () => {
	
	//submit a new request:
    let btn = document.getElementById("submitBtn");

    //to see what the html will send using the logUser function
    document.getElementById("submitBtn").addEventListener("click", submitReimbursement);

    //to ACTAULLY SEND SOMETHING
    btn.addEventListener("click", () => {
        var xhr4 = new XMLHttpRequest();
        xhr4.onreadystatechange = () => {
        };
        xhr4.open("post", "http://localhost:8080/ReimbursementApp/newRequest");
        xhr4.send();
    });
	function submitReimbursement() {
		let description = document.getElementById("description");
		let amount = document.getElementById("amount");

	}
	
	///////////////

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
        if ((xhr.readyState == 4) && (xhr.status == 200)) {
        	
            let employeeInfo = document.getElementById("employeeInfo");
            let employeeFirst = document.getElementById("modal-First-Name");
            let employeeLast = document.getElementById("modal-Last-Name");
            let employeeEmail = document.getElementById("modal-Email");
            let employeeUsername = document.getElementById("modal-Username");
            let response1 = JSON.parse(xhr.responseText);

            //give the form placeholders
            userFormFirstName.placeholder = response1.firstname;
            userFormLastName.placeholder = response1.lastname;
            userFormEmail.placeholder = response1.email;
            userFormPassword.placeholder = response1.password;

        };

    };
    xhr.open("GET", "http://localhost:8080/ReimbursementApp/employee");
    xhr.send();

    var xhr2 = new XMLHttpRequest();
    xhr2.onreadystatechange = () => {
        if ((xhr2.readyState == 4) && (xhr2.status == 200)) {
        	
            let reimbursementInfo = document.getElementById("reimbursementInfo");
            let reimbursmentId = document.getElementById("reimbursmentId");
            let details = document.getElementById("details");
            let amount = document.getElementById("amount");
            let r_managerID = document.getElementById("r_managerID");
            let status = document.getElementById("status");
            let response2 = xhr2.responseText;
//            console.log(response2);
//            console.log(JSON.parse(xhr2.responseText));
            
            response2 = JSON.parse(xhr2.responseText);
            
            let index = 0;

            for (index in response2) {
                let id = response2[index].id;
                let details = response2[index].details;
                let amount = response2[index].amount;
                let status = response2[index].status;


                let row = document.createElement("tr");
                let idcol = document.createElement("td");
                let detailscol = document.createElement("td");
                let amountcol = document.createElement("td");
                let statuscol = document.createElement("td");

                idcol.textContent = id;
                detailscol.textContent = details;
                amountcol.textContent = amount;
                statuscol.textContent = status;

                row.appendChild(idcol);
                row.appendChild(detailscol);
                row.appendChild(amountcol);
                row.appendChild(statuscol);

                document.getElementById("pendingTable").appendChild(row).classList.add("table-warning")

                index++;
            }

        };
    };
    xhr2.open("GET", "http://localhost:8080/ReimbursementApp/pendingReimbursements");
    xhr2.send();
 
    var xhr3 = new XMLHttpRequest();
    xhr3.onreadystatechange = () => {
        if ((xhr3.readyState == 4) && (xhr3.status == 200)) {
        	
            let reimbursementInfo = document.getElementById("reimbursementInfo");
            let reimbursmentId2 = document.getElementById("reimbursmentId2");
            let details2 = document.getElementById("details2");
            let amount2 = document.getElementById("amount2");
            let r_managerID2 = document.getElementById("r_managerID2");
            let status2 = document.getElementById("status2");
            let response3 = xhr3.responseText;

            response3 = JSON.parse(xhr3.responseText);

            

            let index2 = 0;

            for (index2 in response3) {
                let id2 = response3[index2].id;
                let details2 = response3[index2].details;
                let amount2 = response3[index2].amount;
                let status2 = response3[index2].status;

                let row2 = document.createElement("tr");
                let idcol2 = document.createElement("td");
                let detailscol2 = document.createElement("td");
                let amountcol2 = document.createElement("td");
                let statuscol2 = document.createElement("td");

                idcol2.textContent = id2;
                detailscol2.textContent = details2;
                amountcol2.textContent = amount2;
                statuscol2.textContent = status2;

                row2.appendChild(idcol2);
                row2.appendChild(detailscol2);
                row2.appendChild(amountcol2);
                row2.appendChild(statuscol2);

                document.getElementById("approvedTable").appendChild(row2).classList.add("table-success");
                index2++;
            }

        };
    };
    xhr3.open("GET", "http://localhost:8080/ReimbursementApp/approvedReimbursements");
    xhr3.send();
  
}
