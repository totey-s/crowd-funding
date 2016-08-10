<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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

    <div class="login-page">
  <div class="form">    
<%--     <form:form method="POST" action="login.htm" commandName="login" class="login-form"> --%>
<%--       <form:input path="username" placeholder="username"/><font color="red"><form:errors path="username"/></font> --%>
<%--       <form:input path="password" placeholder="password"/><font color="red"><form:errors path="password"/></font> --%>
<!--       <input class="button" type="submit" value="Login" /> -->
<!--       <p class="message">Not registered? <a id="register" href="register.htm">Create an account</a></p> -->
<%--     </form:form> --%>
		<form:form method="post" action="login.htm" commandName="user">
            <form:input path="username" placeholder="username"/>
            <form:input path="password" placeholder="password"/>
            <form:input path="email" placeholder="email"/>
            <input class="button" type="submit" value="Login" />
            <div style="color:red">${error}</div>
        </form:form>
  </div>
<!--   <a href="register.htm">Register</a>   -->
  	
</div>


</body>
</html>