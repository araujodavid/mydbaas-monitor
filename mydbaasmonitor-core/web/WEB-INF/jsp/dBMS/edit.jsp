<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="author" content="David Araújo"/>
		<meta name="reply-to" content="araujodavid@lia.ufc.br"/>
		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">
		
		<title>MyDBaaSMonitor - Edit DBMS</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>

		<div class="container" style="margin-top:70px;">		
			<form method="post" action="<c:url value="/dbmss/update"/>">
				<fieldset>
					<legend>
						<strong>Editing DBMS</strong>
					</legend>
					
					<c:if test="${errors != null}">							
						<c:forEach var="error" items="${errors}">
							<div class="alert alert-error">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>${error.category}!</strong> ${error.message}
							</div>
		  				</c:forEach>			  				
	  				</c:if>
	  					  				
	  				<input name="dbms.id" id="id" type="hidden" value="${dbms.id}" />
	  				
	  				<c:choose>
	  					<c:when test="${availableVMs.isEmpty()}">
	  						<input name="dbms.machine.id" id="id" type="hidden" value="${dbms.machine.id}" />
	  					</c:when>
	  					<c:otherwise>
	  						<label class="text-info" for="machine">Virtual Machines:</label>
			  				<select id="machine" name="dbms.machine.id" style="width:284px;">
			  					<option value="0" selected="selected">Select one</option>
			  					<c:forEach var="machine" items="${availableVMs}">
									<option value="${machine.id}" <c:if test="${machine.id == dbms.machine.id}">selected="selected"</c:if> >
										${machine.alias}
									</option>
				  				</c:forEach>
			  				</select>
	  					</c:otherwise>
	  				</c:choose>
	  				
	  				<label class="text-info" for="type">Database Type:</label>
	  				<select id="type" name="dbms.type" style="width:284px;">
						<option value="Mysql" <c:if test="${dbms.type.equals('MySQL')}">selected="selected"</c:if>>MySQL</option>
						<option value="PostgreSQL" <c:if test="${dbms.type.equals('PostgreSQL')}">selected="selected"</c:if>>PostgreSQL</option>
	  				</select>
	  				
	  				<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="dbms.alias" id="alias" type="text" value="${dbms.alias}" placeholder="To better identify the resource" />
					<span class="help-block"><em><small>Example: DBMS Project X</small></em></span>
					
					<label class="text-info" for="user">User:</label>
					<input name="dbms.user" id="user" type="text" value="${dbms.user}" placeholder="DBMS username" />
					
					<label class="text-info" for="port">Port:</label>
					<input class="input-small" name="dbms.port" id="port" type="text" value="${dbms.port}" placeholder="e.g. 3604" />							
					<span class="help-block"><em><small>Enter the DBMS port.</small></em></span>
					
					<label class="text-info" for="password">Password:</label>
					<input name="dbms.password" id="password" type="text" value="${dbms.password}" /> <br>
					<input name="confirmPassword" id="confirmPassword" type="text" placeholder="Confirm the password" />
					
					<label class="text-info" for="description">Description:</label>	  							
 					<textarea name="dbms.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;">${dbms.description}</textarea>					
					
					<div class="form-actions">
						<button type="submit" class="btn btn-success">Save</button>
						<a class="btn btn-danger" href="<c:url value="/dbmss/view/${dbms.id}"/>" onclick="return confirm('Are you sure want to cancel the update?');">Cancel</a>
					</div>					  	
	  			</fieldset>
		 	</form>
	 	</div> <!-- /container -->
		
		<%@include file="/static/footer.jsp"%>
	 	
	 	<script src="http://code.jquery.com/jquery-latest.js"></script>
    	<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
	</body>
</html>