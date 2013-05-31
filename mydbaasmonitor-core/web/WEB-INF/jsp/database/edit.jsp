<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="author" content="David Araújo"/>
		<meta name="reply-to" content="araujodavid@lia.ufc.br"/>
		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">
		
		<title>MyDBaaSMonitor - Edit Database</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>

		<div class="container" style="margin-top:70px;">		
			<form method="post" action="<c:url value="/databases/update"/>">
				<fieldset>
					<legend>
						<strong>Editing Database</strong>
					</legend>
					
					<c:if test="${errors != null}">							
						<c:forEach var="error" items="${errors}">
							<div class="alert alert-error">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>${error.category}!</strong> ${error.message}
							</div>
		  				</c:forEach>			  				
	  				</c:if>
	  					  				
	  				<input name="database.id" id="id" type="hidden" value="${database.id}" />
	  				
	  				<c:choose>
	  					<c:when test="${availableDBMSs.isEmpty()}">
	  						<input name="database.dbms.id" id="id" type="hidden" value="${database.dbms.id}" />
	  					</c:when>
	  					<c:otherwise>
	  						<label class="text-info" for="dbms">Database Management System:</label>
			  				<select id="dbms" name="database.dbms.id" style="width:284px;">
			  					<c:forEach var="dbms" items="${availableDBMSs}">
									<option value="${dbms.id}" <c:if test="${dbms.id == database.dbms.id}">selected="selected"</c:if> >
										${dbms.alias}
									</option>
				  				</c:forEach>
			  				</select>
	  					</c:otherwise>
	  				</c:choose>
	  				
	  				<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="database.alias" id="alias" type="text" value="${database.alias}" placeholder="To better identify the resource" />
					<span class="help-block"><em><small>Example: Database Project X</small></em></span>
					
					<label class="text-info" for="name">Name:</label>
					<input name="database.name" id="name" type="text" value="${database.name}" placeholder="Schema identifier" />
					
					<label class="text-info" for="description">Description:</label>	  							
 					<textarea name="database.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;">${database.description}</textarea>					
					
					<div class="form-actions">
						<button type="submit" class="btn btn-success">Save</button>
						<a class="btn btn-danger" href="<c:url value="/dbmss/view/${dbms.id}"/>" onclick="return confirm('Are you sure want to cancel the update?');">Cancel</a>
					</div>					  	
	  			</fieldset>
		 	</form>
	 	</div> <!-- /container -->
		
		<%@include file="/static/footer.jsp"%>
	 	<%@include file="/static/javascript_footer.jsp"%>
	 	
	</body>
</html>