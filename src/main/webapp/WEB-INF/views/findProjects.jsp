<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  -->
<link rel="shortcut icon" href="resources/images/ico/favicon.ico">
	<c:if test="${not empty requestScope.Search}">
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
     <script src="resources/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/dashboard/custom.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="resources/js/dashboard/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="resources/js/dashboard/ie10-viewport-bug-workaround.js"></script>
</c:if>    
</head>
<body>
<jsp:include page="top_menu.jsp" />

	<div class="container" style="padding-top: 2%;">
		<div class="row">
			<div class="col-md-4 col-sm-6 col-xs-12">
				<h1>${requestScope.Title}</h1>
			</div>
		</div>
		<div class="row">
		  
		  <c:choose>
		  	<c:when test="${not empty requestScope.Search}">
		  		<c:forEach items="${requestScope.projects}" var="proj">
		  			<c:if test="${proj.approved ne false}">
		  				<div class="col-sm-6 col-md-4">
				  	<div class="thumbnail">
				      <img src="${proj.projectPic}" alt="${proj.projectName}" />
				      <div class="caption">
				        <h3>${proj.projectName}</h3>
				        <p>By ${proj.owner_Fname} ${proj.owner_Lname}</p>
				        <p>${proj.description}</p>
				        <p>
				        	<c:set value="0" var="amount"></c:set>
				        	<c:forEach items="${proj.fundReceived}" var ="funds">
				        		<c:set value="${amount+funds.fundAmount}" var="amount"></c:set>		        		
				        	</c:forEach>
				        	Pledged: <strong>\$${amount}</strong>
				        	Backers: <strong>${proj.backers}</strong>
				        	<span></span>
				        </p>
				        <c:choose>
				        	<c:when test="${proj.approved eq false}">
				        		<p><font color="red">This Project is not yet approved</font></p>
				        	</c:when>
				        	<c:otherwise>
				        		<c:if test="${proj.owner.userId ne sessionScope.user.userId}">
						        	<p><a href="payment.htm?projectId=${proj.projectId}" class="btn btn-success" role="button">Back This Project</a></p>
						        </c:if>	
				        	</c:otherwise>
				        </c:choose>
				        
				      </div>
				    </div>
				    </div>
		  			</c:if>
				  </c:forEach>		  	
		  	</c:when>
		  	<c:otherwise>
		  		<c:forEach items="${requestScope.projects}" var="proj">		  
				  <div class="col-sm-6 col-md-4">
				  	<div class="thumbnail">
				      <img src="${proj.projectPic}" alt="${proj.projectName}" />
				      <div class="caption">
				        <h3>${proj.projectName}</h3>
				        <p>By ${proj.owner_Fname} ${proj.owner_Lname}</p>
				        <p>${proj.description}</p>
				        <p>
				        	<c:set value="0" var="amount"></c:set>
				        	<c:forEach items="${proj.fundReceived}" var ="funds">
				        		<c:set value="${amount+funds.fundAmount}" var="amount"></c:set>		        		
				        	</c:forEach>
				        	Pledged: <strong>\$${amount}</strong>
				        	Backers: <strong>${proj.backers}</strong>
				        	<span></span>
				        </p>
				        <c:choose>
				        	<c:when test="${proj.approved eq false}">
				        		<p><font color="red">This Project is not yet approved</font></p>
				        	</c:when>
				        	<c:otherwise>
				        		<c:if test="${proj.owner.userId ne sessionScope.user.userId}">
						        	<p><a href="payment.htm?projectId=${proj.projectId}" class="btn btn-success" role="button">Back This Project</a></p>
						        </c:if>	
				        	</c:otherwise>
				        </c:choose>
				        
				      </div>
				    </div>
				    </div>
				  </c:forEach>
		  	</c:otherwise>
		  </c:choose>
		  
		    
		  
		</div>	
	</div>
</body>
</html>