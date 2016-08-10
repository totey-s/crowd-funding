<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Credit card validation with card.js</title>
    <link rel="stylesheet" href="resources/css/payment/style.css">    
  </head>
  <body>

    <body>
<!--   <div class="body-text">Try to write your name in the name fields. Also try to write your card number. This plugin identifies your card and shows the right graphics. By clicking CCV field card will turn.</div> -->
  <form:form modelAttribute="paymentDetails" action="payment.htm">
    <div class="form-container">
      <div class="personal-information">
        <h1>Payment Information</h1>
      </div> <!-- end of personal-information -->
      <input type="text" name="amount" placeholder="Funding Amount"/>
      
<!--       <input id="input-field" type="text" name="streetaddress" required="required" autocomplete="on" maxlength="45" placeholder="Streed Address"/>       -->
		<form:input path="streetAddress" autocomplete="on" maxlength="45" placeholder="Streed Address"/><font color="red"><form:errors path="streetAddress"/></font>
		
<!--       <input id="column-left" type="text" name="city" required="required" autocomplete="on" maxlength="20" placeholder="City"/> -->
      <form:input id="column-left" path="city" autocomplete="on" maxlength="20" placeholder="City"/><font color="red"><form:errors path="city"/></font>

<!--       <input id="column-right" type="text" name="zipcode" required="required" autocomplete="on" pattern="[0-9]*" maxlength="5" placeholder="ZIP code"/> -->
	<form:input id="column-right" path="zipcode" autocomplete="on" pattern="[0-9]*" maxlength="5" placeholder="ZipCode"/><font color="red"><form:errors path="zipcode"/></font>
      
<!--       <input id="input-field" type="email" name="email" required="required" autocomplete="on" maxlength="40" placeholder="Email"/> -->
	<form:input path="email" autocomplete="on" maxlength="40" placeholder="Email"/><font color="red"><form:errors path="email"/></font>
    
    <div class="card-wrapper"></div>
      <input id="column-left" type="text" name="first-name" placeholder="First Name"/>
<%-- 	<form:input path="firstName" id="column-left" name="first-name" placeholder="First Name"/><font color="red"><form:errors path="firstName"/></font> --%>
      
      <input id="column-right" type="text" name="last-name" placeholder="Surname"/>
<%-- 	<form:input path="lastName" id="column-right" name="last-name" placeholder="Last Name"/><font color="red"><form:errors path="lastName"/></font> --%>
      
      <input id="input-field" type="text" name="number" placeholder="Card Number"/>
<%-- 	<form:input path="cardNumber" name="number" placeholder="Card Number"/><font color="red"><form:errors path="cardNumber"/></font> --%>
     
      <input id="column-left" type="text" name="expiry" placeholder="MM / YY"/>
<%-- 	<form:input path="expiry" placeholder="MM / YY"/><font color="red"><form:errors path="expiry"/></font> --%>
        
      <input id="column-right" type="text" name="cvc" placeholder="CCV"/>
    	<input type="hidden" name="projectId" value="${requestScope.projectId}"/>
      <input id="input-button" type="submit" value="Submit"/>
    </form:form>
    <a href="userDash.htm">Back to Dashboard</a>
  </div> <!-- end of form-container -->
</body>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/121761/card.js'></script>
<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/121761/jquery.card.js'></script>

        <script src="resources/js/payment/index.js"></script>

    
    
    
  </body>
</html>
