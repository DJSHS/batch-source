let reimId = sessionStorage.getItem("reimId");
let token = sessionStorage.getItem("token");
const tokenArr = token.split(':');
const managerId = tokenArr[0];

document.getElementById("approve-btn").addEventListener("click", approveReq);
document.getElementById("denial-btn").addEventListener("click", denyReq);
document.getElementById("resolve-return-btn").addEventListener("click", returnHome);

if(!reimId){
	window.location.href="http://localhost:8080/Project1/home";
} else {
	let url = "http://localhost:8080/Project1/api/reimbursements?id=" + reimId;
	sendAjaxGetPendingReim(url, displayPendingById);
}

function returnHome() {
	window.location.href="http://localhost:8080/Project1/home";
}

function sendAjaxGetPendingReim(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status === 200) {
			callback(this);
		} else if (this.readyState === 4){
			window.location.href="http://localhost:8080/Project1/login";
		}
	}

	xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

function displayPendingById(xhr) {
	let r = JSON.parse(xhr.response);
	document.getElementById("empl-row").innerHTML= `<td>${r.reimId}</td>
					<td>${r.submitBy.firstName} ${r.submitBy.lastName}</td>
					<td>${r.submitBy.emplId}</td>
					<td>${r.submitBy.position}</td>
					<td>${r.submitBy.department.deptName}</td>`;
	document.getElementById("reim-row").innerHTML= `<td>${r.amount}</td>
					<td>${r.submitDate.slice(0, 16)}</td>
					<td colspan="3">${r.description}</td>`;
}

function approveReq() {
	if (window.confirm("Approve the request")) { 
		sendAjaxUpdateReim('approved');
	}
}

function denyReq() {
	if (window.confirm("Deny the request")) { 
		sendAjaxUpdateReim('denial');
	}
}

function sendAjaxUpdateReim(result) {
	let url = "http://localhost:8080/Project1/reimbursement/update";
	let xhr = new XMLHttpRequest();
	xhr.open("PUT", url);
	
	xhr.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 204) {
			window.location.href="http://localhost:8080/Project1/home";
		}
		
		else if (this.readyState === 4 && this.status !== 204){
			alert('Failed');
		}
	}
	
	let reason = document.getElementById('reason').value;
	
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	let requestBody = `reimId=${reimId}&managerId=${managerId}&result=${result}&reason=${reason}`;
	console.log(requestBody)
	xhr.send(requestBody);
}


