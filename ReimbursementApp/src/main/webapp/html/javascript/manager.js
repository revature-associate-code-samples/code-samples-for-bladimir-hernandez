
window.onload = () => {


    var xhr4 = new XMLHttpRequest();
    xhr4.onreadystatechange = () => {
        if ((xhr4.readyState == 4) && (xhr4.status == 200)) {

            let employeeInfo = document.getElementById("employeeInfo");
            let employeeFirst = document.getElementById("modal-First-Name");
            let employeeLast = document.getElementById("modal-Last-Name");
            let employeeEmail = document.getElementById("modal-Email");
            let employeeUsername = document.getElementById("modal-Username");
            let response4 = JSON.parse(xhr4.responseText);

            //give the form placeholders
            userFormFirstName.placeholder = response4.firstname;
            userFormLastName.placeholder = response4.lastname;
            userFormEmail.placeholder = response4.email;
            userFormPassword.placeholder = response4.password;

        };

    };
    xhr4.open("GET", "http://localhost:8080/ReimbursementApp/employee");
    xhr4.send();




    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
        if ((xhr.readyState == 4) && (xhr.status == 200)) {

            //            let reimbursementInfo = document.getElementById("reimbursementInfo");
            let reimbursmentId = document.getElementById("reimbursmentId");
            let details = document.getElementById("details");
            let amount = document.getElementById("amount");
            let r_managerID = document.getElementById("r_managerID");
            let status = document.getElementById("status");
            let response = xhr.responseText;
            //console.log(response);
            //            console.log(JSON.parse(xhr.responseText));

            response = JSON.parse(xhr.responseText);

            let index = 0;

            for (index in response) {
                let id = response[index].id;
                let details = response[index].details;
                let amount = response[index].amount;
                let status = response[index].status;


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

                document.getElementById("allPending").appendChild(row).classList.add("table-warning");
                index++;
            }

        };
    };
    xhr.open("GET", "http://localhost:8080/ReimbursementApp/allPendingRequests");
    xhr.send();
    var xhr3 = new XMLHttpRequest();
    xhr3.onreadystatechange = () => {
        if ((xhr3.readyState == 4) && (xhr3.status == 200)) {

            let reimbursmentId = document.getElementById("id");
            let details = document.getElementById("details");
            let amount = document.getElementById("amount");
            let r_employeeID = document.getElementById("r_employeeID");
            let r_managerID = document.getElementById("r_managerID");
            let status = document.getElementById("status");
            let response3 = xhr3.responseText;

            response3 = JSON.parse(xhr3.responseText);

            let index3 = 0;

            for (index3 in response3) {
                let id = response3[index3].id;
                let details = response3[index3].details;
                let amount = response3[index3].amount;
                let status = response3[index3].status;


                let row3 = document.createElement("tr");
                let idcol = document.createElement("td");
                let detailscol = document.createElement("td");
                let amountcol = document.createElement("td");
                let statuscol = document.createElement("td");

                idcol.textContent = id;
                detailscol.textContent = details;
                amountcol.textContent = amount;
                statuscol.textContent = status;

                row3.appendChild(idcol);
                row3.appendChild(detailscol);
                row3.appendChild(amountcol);
                row3.appendChild(statuscol);

                document.getElementById("allApproved").appendChild(row3).classList.add("table-success")
                index3++;
            }
        };
    };
    xhr3.open("GET", "http://localhost:8080/ReimbursementApp/allApprovedRequests");
    xhr3.send();

    var xhr2 = new XMLHttpRequest();
    xhr2.onreadystatechange = () => {
        if ((xhr2.readyState == 4) && (xhr2.status == 200)) {

            let reimbursementInfo = document.getElementById("reimbursementInfo");
            let employeeId = document.getElementById("employeeId");
            let firstName = document.getElementById("firstName");
            let lastName = document.getElementById("lastName");
            let email = document.getElementById("email");
            let username = document.getElementById("username");
            let response2 = xhr2.responseText;




            response2 = JSON.parse(xhr2.responseText);

            let index2 = 0;

            for (index2 in response2) {
                let id = response2[index2].id;
                let firstName = response2[index2].firstname;
                let lastName = response2[index2].lastname;
                let email = response2[index2].email;
                let username = response2[index2].username;


                let row2 = document.createElement("tr");
                let idcol = document.createElement("td");
                let firstNamecol = document.createElement("td");
                let lastNamecol = document.createElement("td");
                let emailcol = document.createElement("td");
                let usernamecol = document.createElement("td");

                idcol.textContent = id;
                firstNamecol.textContent = firstName;
                lastNamecol.textContent = lastName;
                emailcol.textContent = email;
                usernamecol.textContent = username;

                row2.appendChild(idcol);
                row2.appendChild(firstNamecol);
                row2.appendChild(lastNamecol);
                row2.appendChild(emailcol);
                row2.appendChild(usernamecol);

                document.getElementById("allEmployees").appendChild(row2);

                index2++;
            }
        };
    };
    xhr2.open("GET", "http://localhost:8080/ReimbursementApp/allEmployees");
    xhr2.send();

    ////// SEARCH FOR A SINGLE USER
    let btn2 = document.getElementById("search");

    //    document.getElementById("display");

    btn2.addEventListener("click", () => {
        console.log("it is in the xhr5!");

        var obj = { id: document.getElementById("employeeid").value };

        var xhr5 = new XMLHttpRequest();
        xhr5.onreadystatechange = () => {
            if ((xhr5.readyState == 4) && (xhr5.status == 200)) {
                let id = document.getElementById("id");
                let details = document.getElementById("details");
                let amount = document.getElementById("amount");
                let r_managerID = document.getElementById("r_managerID");
                let status = document.getElementById("status");
                let response5 = xhr5.responseText;

                response5 = JSON.parse(response5);
                console.log(response5);
                let index5 = 0;
                for (index5 in response5) {
                    let id2 = response5[index5].id;

                    let details = response5[index5].details;
                    let amount = response5[index5].amount;
                    let status = response5[index5].status;


                    let row5 = document.createElement("tr");
                    let idcol5 = document.createElement("td");
                    let detailscol5 = document.createElement("td");
                    let amountcol5 = document.createElement("td");
                    let statuscol5 = document.createElement("td");

                    idcol5.textContent = id;
                    detailscol5.textContent = details;
                    amountcol5.textContent = amount;
                    statuscol5.textContent = status;

                    row5.appendChild(idcol5);
                    row5.appendChild(detailscol5);
                    row5.appendChild(amountcol);
                    row5.appendChild(statuscol);

                    document.getElementById("searchedUsers").appendChild(row5);

                    index5++;
                }
            };
        };
        xhr5.open("POST", "http://localhost:8080/ReimbursementApp/searchedUser");
        xhr5.send(JSON.stringify(obj));
        ////// aprove a reimbursement
        let btn6 = document.getElementById("approve");


        btn6.addEventListener("click", () => {
            console.log("it is in the xhr6!");

            var obj2 = { id: document.getElementById("reimbursementid").value };

            var xhr6 = new XMLHttpRequest();
            xhr6.onreadystatechange = () => {
                if ((xhr6.readyState == 4) && (xhr6.status == 200)) {
                    let id = document.getElementById("id");
                    let details = document.getElementById("details");
                    let amount = document.getElementById("amount");
                    let r_managerID = document.getElementById("r_managerID");
                    let status = document.getElementById("status");
                    let response6 = xhr6.responseText;

                    response6 = JSON.parse(response6);
                    console.log(response6);
                    let index6 = 0;
                    for (index6 in response6) {
                        let id3 = response6[index6].id;

                        let details = response6[index6].details;
                        let amount = response6[index6].amount;
                        let status = response6[index6].status;


                        let row6 = document.createElement("tr");
                        let idcol6 = document.createElement("td");
                        let detailscol6 = document.createElement("td");
                        let amountcol6 = document.createElement("td");
                        let statuscol6 = document.createElement("td");

                        idcol5.textContent = id3;
                        detailscol5.textContent = details;
                        amountcol5.textContent = amount;
                        statuscol5.textContent = status;

                        row6.appendChild(idcol6);
                        row6.appendChild(detailscol6);
                        row6.appendChild(amountcol6);
                        row6.appendChild(statuscol6);

                        document.getElementById("searchedUsers").appendChild(row6);

                        index6++;
                    }
                };
            };
            xhr6.open("POST", "http://localhost:8080/ReimbursementApp/approveReimbursement");
            xhr6.send(JSON.stringify(obj2));
        	
            ////// aprove a reimbursement
            let btn6 = document.getElementById("approve");


            btn6.addEventListener("click", () => {
                console.log("it is in the xhr6!");

                var obj2 = { id: document.getElementById("reimbursementid").value };

                var xhr6 = new XMLHttpRequest();
                xhr6.onreadystatechange = () => {
                    if ((xhr6.readyState == 4) && (xhr6.status == 200)) {
                        let id = document.getElementById("id");
                        let details = document.getElementById("details");
                        let amount = document.getElementById("amount");
                        let r_managerID = document.getElementById("r_managerID");
                        let status = document.getElementById("status");
                        let response6 = xhr6.responseText;

                        response6 = JSON.parse(response6);
                        console.log(response6);
                        let index6 = 0;
                        for (index6 in response6) {
                            let id3 = response6[index6].id;

                            let details = response6[index6].details;
                            let amount = response6[index6].amount;
                            let status = response6[index6].status;


                            let row6 = document.createElement("tr");
                            let idcol6 = document.createElement("td");
                            let detailscol6 = document.createElement("td");
                            let amountcol6 = document.createElement("td");
                            let statuscol6 = document.createElement("td");

                            idcol6.textContent = id3;
                            detailscol6.textContent = details;
                            amountcol6.textContent = amount;
                            statuscol6.textContent = status;

                            row6.appendChild(idcol6);
                            row6.appendChild(detailscol6);
                            row6.appendChild(amountcol6);
                            row6.appendChild(statuscol6);

                            document.getElementById("searchedUsers").appendChild(row6);

                            index6++;
                        }
                    };
                };
                xhr6.open("POST", "http://localhost:8080/ReimbursementApp/approveReimbursement");
                xhr6.send(JSON.stringify(obj2));
                //         console.log(JSON.stringify(obj2));     
            
            });
        });
    })
}