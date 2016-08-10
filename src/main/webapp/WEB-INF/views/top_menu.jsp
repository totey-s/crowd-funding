<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="home.htm">KickStarter</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form action="search.htm"  class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" required="required" name="searchString" class="form-control" placeholder="Search Projects" />
        </div>
        <input type="submit" value="Search" class="btn btn-default" />
      </form>
      <c:choose>
      	<c:when test="${not empty sessionScope.user && requestScope.access ne 'blocked'}">
      	<ul class="nav navbar-nav navbar-right">
        	<li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.user.firstName} ${sessionScope.user.lastName}<span class="caret"></span></a>
        		<ul class="dropdown-menu">        			
        				<%-- <c:if test="${sessionScope.user.userRole.role == 'ROLE_USER'}">
        					<li><a href="userDash.htm">My Account</a></li>	
        				</c:if>
        				<c:if test="${sessionScope.user.userRole.role == 'ROLE_ADMIN'}">
        					<li><a href="adminDash.htm">My Dashboard</a></li>
        				</c:if> --%>
        			<li><a href="userDash.htm">My Account</a></li>
		            <li><a href="logout.htm">Logout</a></li>
		          </ul>
        	</li>
        </ul>	        	
        </c:when>
        <c:otherwise>
      		<ul class="nav navbar-nav navbar-right">
		        <li><a href="login.htm">Login</a></li>
		        <li><a href="register.htm">Register</a></li>
	        </ul>  
        </c:otherwise>
      </c:choose>      
      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>