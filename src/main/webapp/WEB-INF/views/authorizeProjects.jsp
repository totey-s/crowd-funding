<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<script>
function approveProject(projId){
	$.ajax({
		url:"approve.htm",
        type: 'POST',
//        dataType: 'json',
        data: "projectId="+projId,
        success:function(response){
        	alert(response);
        }
	});
}

function getProjects(status){
	$.ajax({
		url:"getProjects.htm",
        type: 'POST',
       	dataType: 'json',
//        	data: "Status="+status,
        data: "{\"approved\":"+status+"}",
        contentType: 'application/json',
//         mimeType: 'application/json',
        success:function(response){
        	var tr = "";
        	for (var count = 0; count < response.length; count++) {
        		tr+="<tr>"+
        			"<td>"+response[count].projectId+"</td>"+
        			"<td>"+response[count].projectName+"</td>"+
        			"<td>"+response[count].categoryName+"</td>"+
        			"<td>"+response[count].description+"</td>"+
        			"<td>"+response[count].location+"</td>"+
        			"<td>"+response[count].fundGoal+"</td>"+
        			"<td>"+response[count].duration+"</td>"+
        			"<td>"+response[count].owner_Fname+" "+response[count].owner_Lname+"</td>"+
        			"<td>"+response[count].createdDate+"</td>"+
        			"<td>"+response[count].approved+"</td>"+
//         			"<td>NA</td>"+
        		"</tr>";
        	}
        	$(".table tbody").html(tr);
        	if($("#getProjectsLink").hasClass("getApproved")){
        		$("#getProjectsLink").attr("onclick", "getProjects('false')");
            	$("#getProjectsLink").html("Get Projects Pending Approval");
            	$("#getProjectsLink").removeClass("getApproved").addClass("getRequests");
        	}else{
        		$("#getProjectsLink").attr("onclick", "getProjects('true')");
            	$("#getProjectsLink").html("Get Approved Projects");
            	$("#getProjectsLink").removeClass("getRequests").addClass("getApproved");
        	}        	
        }
	});
}
</script>

<div class="col-md-8 col-sm-10 col-xs-12 personal-info">     
<a id="getProjectsLink" class="getApproved btn btn-primary" onclick="getProjects('true')">Get Approved Projects</a> 
<table class="table table-striped">
	<thead>
      <tr>
      	<th>Project Id</th>
        <th>Project Name</th>
        <th>Category</th>
        <th>Description</th>
        <th>Location</th>
        <th>Fund Goal</th>
        <th>Fund Duration</th>
        <th>Owner</th>
        <th>Created Date</th>
        <th>Approval Status</th>      
        <th></th>  
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${requestScope.projects}" var="proj">
    		<tr>
    			<td>${proj.projectId}</td>
    			<td>${proj.projectName}</td>
    			<td>${proj.categoryName}</td>
    			<td>${proj.description}</td>
    			<td>${proj.location}</td>
    			<td>${proj.fundGoal}</td>
    			<td>${proj.duration}</td>
    			<td>${proj.owner.firstName} ${proj.owner.lastName}</td>
    			<td>${proj.createdDate}</td>
    			<td>${proj.approved}</td>
    			<td><a onclick="approveProject('${proj.projectId}')">Approve</a>
    		</tr>
    	</c:forEach>
    </tbody>
</table>   
        
</div>