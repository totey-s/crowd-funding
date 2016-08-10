<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
  <head>
    <title>My Account</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="resources/css/dashboard/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/dashboard/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="resources/css/dashboard/ie-emulation-modes-warning.js"></script>
        <!-- styles -->

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
       
  </head>
  <body>
  <jsp:include page="top_menu.jsp" />

<div class="container-fluid" style="padding-top: 2%;">
	<div class="row">
		<c:if test="${sessionScope.user.userRole.role == 'ROLE_USER'}">
			<jsp:include page="user-dashboard-sidebar.jsp"></jsp:include>
		</c:if>
		<c:if test="${sessionScope.user.userRole.role == 'ROLE_ADMIN'}">
			<jsp:include page="admin-dashboard-sidebar.jsp"></jsp:include>
		</c:if>
		
    <div class="col-md-2 col-sm-4 col-xs-10">
    </div>
   <div id="main-content">
   			<%-- <jsp:include page="userProfile.jsp"></jsp:include> --%>
    </div>
	</div>
</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
     <script src="resources/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/dashboard/custom.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="resources/js/dashboard/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="resources/js/dashboard/ie10-viewport-bug-workaround.js"></script>
    
    <script type="text/javascript">
    	$(".sidebar ul li").click(function(){
//     		alert("Hi");
    		$(".active").removeClass("active");
    		$("#main-content").load($(this).find("a").attr("class")+".htm");
    		$(this).addClass("active");
    	});
     	$("#main-content").load($(".sidebar ul li.active").find("a").attr("class")+".htm");
    </script>
  </body>
</html>