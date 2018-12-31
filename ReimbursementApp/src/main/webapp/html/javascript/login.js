window.onload = function () {
    let btn = document.getElementById("submitBtn");

    //to see what the html will send using the logUser function
    document.getElementById("submitBtn").addEventListener("click", logUser);

    //to ACTAULLY SEND SOMETHING
    btn.addEventListener("click", () => {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = () => {
        };
               xhr.open("get", "http://localhost:8080/ReimbursementApp/index");
        xhr.send();
    });
}

//this is going to send the user login info
function logUser() {
    let username = document.getElementById("username").value;
    let pass = document.getElementById("password").value;
}






