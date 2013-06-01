<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="author" content="David Araújo"/>
		<meta name="reply-to" content="araujodavid@lia.ufc.br"/>
		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">
		
		<title>MyDBaaSMonitor - New Database</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>

		<div class="container" style="margin-top:70px;">		
			<form method="post" action="<c:url value="/databases/add"/>">
				<fieldset>
					<legend><strong>New Database</strong></legend>
					
					<c:if test="${errors != null}">							
						<c:forEach var="error" items="${errors}">
							<div class="alert alert-error">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>${error.category}!</strong> ${error.message}
							</div>
		  				</c:forEach>			  				
	  				</c:if>
	  				
 					<label class="text-info" for="dbms">Database Management System:</label>
	  				<select id="dbms" name="entity.dbms.id" style="width:284px;">
	  					<option value="0" selected="selected">Select one</option>
	  					<c:forEach var="dbms1" items="${availableDBMSs}">
							<option value="${dbms1.id}" <c:if test="${dbms1.id == entity.dbms.id}">selected="selected"</c:if> >
								${dbms1.alias}
							</option>
		  				</c:forEach>
	  				</select>
	  				<c:if test="${availableDBMSs == null || availableDBMSs.isEmpty()}">
	  					<span class="help-block"><em><small class="text-error">There is no registered DBMSs. Create a new <a href="<c:url value="/dbmss/new" />">here</a>.</small></em></span>
	  				</c:if>
	  				
	  				<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="entity.alias" id="alias" type="text" value="${entity.alias}" placeholder="To better identify the resource" />
					<span class="help-block"><em><small>Example: Database DBMS X</small></em></span>
					
					<label class="text-info" for="name">Name:</label>
					<input name="entity.name" id="name" type="text" value="${entity.name}" placeholder="Schema identifier" />
					
					<label class="text-info" for="description">Description:</label>	  							
 					<textarea name="entity.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;">${entity.description}</textarea>
					
					<label class="text-info" for="recordDate">Record Date:</label>
					<input class="input-small" name="entity.recordDate" id="recordDate" type="text" readonly="readonly" value="${current_date}" />						
					
					<div class="form-actions">
						<button type="submit" class="btn btn-success">Save</button>
						<a class="btn btn-danger" href="<c:url value="/databases"/>" onclick="return confirm('Are you sure want to cancel the registration?');">Cancel</a>
					</div>					  	
	  			</fieldset>
		 	</form>
	 	</div> <!-- /container -->
		
		<%@include file="/static/footer.jsp"%>
	 	<%@include file="/static/javascript_footer.jsp"%>
	</body>
</html>