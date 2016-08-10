<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form class="form-horizontal" method="post" modelAttribute="createdProject" action="uploadProject.htm" enctype="multipart/form-data" role="form">
<%--     <form:form> --%>
    <!-- edit form column -->    
    <div class="col-md-8 col-sm-10 col-xs-12 personal-info">
      <h3>Personal info</h3>
      
        <div class="form-group">
<!--           <label class="col-lg-3 control-label">First name:</label> -->
          <div class="col-lg-8">			
			<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">Project Image</h3>
			  </div>
			  <div class="panel-body">
			    <input type="file" name="projectPic" class="text-center center-block well well-sm" />
			  </div>
			</div>            
          </div>
        </div>
        
        <div class="form-group">
          <div class="col-lg-8"><div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">Project Title</h3>
			  </div>
			  <div class="panel-body">
			    <form:input class="form-control" path="projectName" size="30" placeholder="Project Title"/> <font color="red"><form:errors path="projectName"/></font>
			  </div>
			</div>              
          </div>
        </div>      
          
        <div class="form-group">
 		<div class="col-lg-8"><div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">Description</h3>
			  </div>
			  <div class="panel-body">
			    <form:textarea rows="10" cols="40" class="form-control" path="description" size="30" placeholder="Description"/> <font color="red"><form:errors path="description"/></font>
			  </div>
          </div>
          </div>
        </div>
        
        <div class="form-group">
          <div class="col-lg-8"><div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">Category</h3>
			  </div>
			  <div class="panel-body">
			    <form:select class="form-control" path="category" placeholder="Category">
			    	<c:forEach items="${requestScope.categoryList}" var="cat">
			    	<form:option value="${cat.categoryId}">${cat.categoryName}</form:option>
			    	</c:forEach>
			    </form:select>			    
			    <font color="red"><form:errors path="category"/></font>
			  </div>
          </div>
          </div>
        </div>
        
        <div class="form-group">
          <div class="col-lg-8"><div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">Project Location</h3>
			  </div>
			  <div class="panel-body">
			    <form:input class="form-control" path="location" size="30" placeholder="Project Location"/> <font color="red"><form:errors path="location"/></font>
			  </div>
          </div>
          </div>
        </div>
        
        <div class="form-group">
          <div class="col-lg-8"><div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">Funding Goal</h3>
			  </div>
			  <div class="panel-body">
			    <form:input class="form-control" path="fundGoal" size="30" placeholder="Funding Goal"/> <font color="red"><form:errors path="fundGoal"/></font>
			  </div>
          </div>
          </div>
        </div>
        
        <div class="form-group">
          <div class="col-lg-8"><div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">Duration</h3>
			  </div>
			  <div class="panel-body">
			    <form:input class="form-control" path="duration" size="30" placeholder="Funding Duration"/> <font color="red"><form:errors path="duration"/></font>
			  </div>
          </div>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label"></label>
          <div class="col-md-8">
            <input class="btn btn-primary" value="Create Project" type="submit">
            <span></span>
            <input class="btn btn-default" value="Cancel" type="reset">
          </div>
        </div>
      
    </div>
    </form:form>