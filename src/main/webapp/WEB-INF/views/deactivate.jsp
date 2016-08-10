<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style>
.background-image {
  position: fixed;
  left: 0;
  right: 0;
  z-index: 1;

  display: block;
/*   background-image: url('http://666a658c624a3c03a6b2-25cda059d975d2f318c03e90bcf17c40.r92.cf1.rackcdn.com/unsplash_527bf56961712_1.JPG'); */
/*   width: 1200px; */
/*   height: 800px; */

  -webkit-filter: blur(5px);
  -moz-filter: blur(5px);
  -o-filter: blur(5px);
  -ms-filter: blur(5px);
  filter: blur(5px);
}

.content {
  position: fixed;
  left: 0;
  right: 0;
  z-index: 9999;
  margin-left: 20px;
  margin-right: 20px;
}
</style>

<c:if test="${not empty requestScope.deactivate}">
	<div class="background-image">
		<div class="content">
			<jsp:include page="userProfile.jsp"></jsp:include>
		</div>		
	</div>
	<!-- Modal -->
  <div class="modal fade in" id="myModal" role="dialog" style="display:block;top:330px;">
    <div class="modal-dialog">
	<!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
<!--           <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h4 class="modal-title">Deactivate Account</h4>
        </div>
        <div class="modal-body">
          <p>You sure you want to deactivate account?</p>
        </div>
        <div class="modal-footer">
        <form:form action="deactivate.htm" method="post">
        	<input type="submit" value="Yes" class="btn btn-danger" data-dismiss="modal" />
        	<a class="upload btn btn-success" href="#">No</a>
        </form:form>        
          
        </div>
      </div>
      </div>
      </div>
</c:if>