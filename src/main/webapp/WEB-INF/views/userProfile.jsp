<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form class="form-horizontal" method="post" modelAttribute="user" action="upload.htm" enctype="multipart/form-data" role="form">
    <div class="col-md-4 col-sm-6 col-xs-12">
      <div class="text-center">
      <c:choose>
      	<c:when test="${user.profilepic == 'NA'}">
      		<img src="resources/img/dashboard/no-image.gif" class="avatar img-circle img-thumbnail" alt="avatar" />
      	</c:when>
      	<c:otherwise>
      		<img src="${user.profilepic}" class="avatar img-circle img-thumbnail" alt="avatar" style="image-orientation: from-image;"/>
      	</c:otherwise>
      </c:choose>
        
        
        <input type="file" name="profilepic" class="text-center center-block well well-sm">
      </div>
    </div>
    <!-- edit form column -->    
    <div class="col-md-4 col-sm-6 col-xs-12 personal-info">
    <c:if test="${user.profilepic == 'NA'}">
    	<div class="alert alert-info alert-dismissable">
        <a class="panel-close close" data-dismiss="alert">×</a> 
        <i class="fa fa-coffee"></i>
        You currently have not uploaded a profile picture.
      </div>
    </c:if>
      
      <h3>Personal info</h3>
      
        <div class="form-group">
          <label class="col-lg-3 control-label">First name:</label>
          <div class="col-lg-8">
<%--             <input class="form-control" value="${user.firstName}" type="text"> --%>
            <form:input class="form-control" value="${user.firstName}" path="firstName" size="30" placeholder="First Name"/> <font color="red"><form:errors path="firstName"/></font>
          </div>
        </div>
        <div class="form-group">
          <label class="col-lg-3 control-label">Last name:</label>
          <div class="col-lg-8">
<%--             <input class="form-control" value="${user.lastName}" type="text"> --%>
            <form:input class="form-control" value="${user.lastName}" path="lastName" size="30" placeholder="Last Name"/> <font color="red"><form:errors path="lastName"/></font>
          </div>
        </div>        
        <div class="form-group">
          <label class="col-lg-3 control-label">Email:</label>
          <div class="col-lg-8">
<%--             <input class="form-control" value="${user.email}" type="text"> --%>
            <form:input class="form-control" value="${user.email}" path="email" size="30" placeholder="Email"/> <font color="red"><form:errors path="email"/></font>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label">Username:</label>
          <div class="col-md-8">
<%--             <input class="form-control" value="${user.username}" type="text"> --%>
            <form:input class="form-control" value="${user.username}" path="username" size="30" placeholder="Username"/> <font color="red"><form:errors path="username"/></font>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label">Password:</label>
          <div class="col-md-8">
<!--             <input class="form-control" type="password"> -->
            <form:password class="form-control" path="password" size="30" placeholder="Password"/> <font color="red"><form:errors path="password"/></font>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label">Confirm password:</label>
          <div class="col-md-8">
            <input class="form-control" name="confirmPassword" type="password" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label"></label>
          <div class="col-md-8">
          	<form:hidden path="userId" value="${user.userId}"/>
          	<form:hidden path="status" value="${user.status}"/>
          	<%-- <form:hidden path="createdOn" value="${user.createdOn}"/> --%>
<%--           	<form:hidden path="userRole" value="${user.userRole.role}"/> --%>
            <input class="btn btn-primary" value="Save Changes" type="submit">
            <span></span>
            <input class="btn btn-default" value="Cancel" type="reset">
          </div>
        </div>
      
    </div>
    </form:form>