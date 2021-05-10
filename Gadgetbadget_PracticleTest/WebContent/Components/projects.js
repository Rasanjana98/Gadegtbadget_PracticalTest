//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidProjectIDSave").val("");
	$("#PROJECT")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "porjectsAPI",
		type : type,
		data : $("#PROJECT").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});

function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#ProjectGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidProjectIDSave").val("");
	$("#PROJECT")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "projectsAPI",
		type : "DELETE",
		data : "projectID=" + $(this).data("projectID"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#ProjecttGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{
			$("#hidProjectIDSave").val($(this).data("id"));
			$("#projectname").val($(this).closest("tr").find('td:eq(0)').text());
			$("#projectdescription").val($(this).closest("tr").find('td:eq(1)').text());
			$("#requiredfund").val($(this).closest("tr").find('td:eq(2)').text());
			$("#projectduration").val($(this).closest("tr").find('td:eq(3)').text());
				
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// projectname
	if ($("#projectname").val().trim() == "") {
		return "Please insert Project Name.";
	}
	
	// projectdescription
	if ($("#projectdescription").val().trim() == "") {
		return "Please insert project description.";
	}
	
	// requiredfund
	if ($("#requiredfund").val().trim() == "") {
		return "Please insert required fund.";
	}

	// projectduration
	if ($("#projectduration").val().trim() == "") {
		return "Please insert project duration.";
	}
	
	
	
	return true;
}
