<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  <c:choose>
  	<c:when test="${not empty success}">
  		<h2>You have successfully registered!</h2><br/>
  		<a id="signIn" href="home.htm">Home</a>	
  	</c:when>
  	<c:otherwise>
	  	<h2>New User</h2> 
		<form:form action="register.htm" commandName="user" method="post" class="login-form">
	      <form:input path="firstName" size="30" placeholder="First Name"/> <font color="red"><form:errors path="firstName"/></font>
	      <form:input path="lastName" size="30" placeholder="Last Name"/> <font color="red"><form:errors path="lastName"/></font>
	      <form:input path="username" size="30" placeholder="Username"/> <font color="red"><form:errors path="username"/></font>
	      <form:password path="password" size="30" placeholder="Password"/> <font color="red"><form:errors path="password"/></font>
	      <form:input path="email" size="30" placeholder="Email"/> <font color="red"><form:errors path="email"/></font>
	      <input class="button" type="submit" value="Create User" />
	      <p class="message">Already registered? <a id="signIn" href="login.htm">Sign In</a></p>
	    </form:form>	
  	</c:otherwise>
  </c:choose>  
  
</div></div>
</body>
</html>