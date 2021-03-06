<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | Corlate</title>
	
	<!-- core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<!--     <link href="resources/css/font-awesome.min.css" rel="stylesheet" /> -->
<!--     <link href="resources/css/animate.min.css" rel="stylesheet" /> -->
<!--     <link href="resources/css/prettyPhoto.css" rel="stylesheet" /> -->
<!--     <link href="resources/css/main.css" rel="stylesheet" /> -->
	<link href="resources/css/login.css" rel="stylesheet" />
    <link href="resources/css/responsive.css" rel="stylesheet" />
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/images/ico/apple-touch-icon-57-precomposed.png">
    
    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body class="homepage">

<!-- Navigation -->
	<jsp:include page="top_menu.jsp" />

<div class="login-page">
  <div class="form">
<%--   	<c:if test="${not empty error}"> --%>
<!--   		<font color="red">Invalid Username or Password</font>	 -->
<%--   	</c:if> --%>

  	<c:if test="${not empty error}">
		<div class="error"><font color="red">${error}</font></div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>
	
	  	<h2>Verification Code</h2> 
 		<form action="activate.htm" method="post" class="login-form">	      
	      <input type="text" name="code" placeholder="Verification Code" required="required"/>
	      <input type="hidden" value="${requestScope.userId}" name="userId"/>
	      <input class="button" type="submit" value="Activate" />	      
	    </form> 

  	  
  
</div></div>
</body>
</html>