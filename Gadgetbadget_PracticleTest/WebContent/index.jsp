<%@page import="com.gadgetBadgetProjects.model.projectsServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Project Management - GadgetBadget</title>
	
		<link href="myStyle.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/projects.js"></script>

	</head>
	
	<body>
		<div class="container">
	
			<p class="font-weight-bold">
				<center>
					<h1><u><i><b>Projects Management - GadgetBadget</b></i></u></h1>
				</center>
			</p>
			<br><br>
			
			<fieldset>
	
				<legend><b>Add Project Details</b></legend>
					<form id="PROJECT" name="PROJECT" class="border border-light p-5">
				
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Project Name:</label>
						    <input type="text" id="projectname" class="form-control" name="projectname">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Project Description:</label>
						    <input type="text" id="projectdescription" class="form-control" name="projectdescription">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Required Fund:</label>
						    <input type="text" id="requiredfund" class="form-control" name="requiredfund">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Duration:</label>
						    <input type="text" id="projectduration" class="form-control" name="projectduration">						    
						</div>
					
									
						<br> 
						
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						<input type="hidden" id="hidProjectIDSave" name="hidProjectIDSave" value="">
					</form>
				
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>			
			</fieldset>
			
			<br> 
			
			<div class="container" id="ProjectGrid">
				<fieldset>
					<legend><b>View Project Details</b></legend>
					<form method="post" action="projects.jsp" class="table table-striped">
						<%
							projectsServlet viewProjects = new projectsServlet();
											out.print(viewProjects.readProjects());
						%>
					</form>
					<br>
				</fieldset>
			</div>
		</div>
	</body>
</html>



