<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="author" content="David Araújo"/>
		<meta name="reply-to" content="araujodavid@lia.ufc.br"/>
		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">
		
		<title>MyDBaaSMonitor - New DBMS</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>

		<div class="container" style="margin-top:70px;">		
			<form method="post" action="<c:url value="/dbmss/add"/>">
				<fieldset>
					<legend><strong>New DBMS</strong></legend>
					
					<c:if test="${errors != null}">							
						<c:forEach var="error" items="${errors}">
							<div class="alert alert-error">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>${error.category}!</strong> ${error.message}
							</div>
		  				</c:forEach>			  				
	  				</c:if>
	  				
	  				<label class="text-info" for="machine">Virtual Machines:</label>
	  				<select id="machine" name="entity.machine.id" style="width:284px;">
	  					<option value="0" selected="selected">Select one</option>
	  					<c:forEach var="machine" items="${availableVMs}">
							<option value="${machine.id}" <c:if test="${machine.id == entity.machine.id}">selected="selected"</c:if> >
								${machine.alias}
							</option>
		  				</c:forEach>
	  				</select>
	  				<c:if test="${availableVMs == null || availableVMs.isEmpty()}">
	  					<span class="help-block"><em><small class="text-error">There is no registered Virtual Machines. Create a new <a href="<c:url value="/vms/new" />">here</a>.</small></em></span>
	  				</c:if>  				
	  				
	  				<label class="text-info" for="type">DBMS Type:</label>
	  				<select id="type" name="entity.type" style="width:284px;">	  					
	  					<option value="select" <c:if test="${entity.type.equals('select')}">selected="selected"</c:if>>Select one</option>
						<option value="Mysql" <c:if test="${entity.type.equals('MySQL')}">selected="selected"</c:if>>MySQL</option>
						<option value="PostgreSQL" <c:if test="${entity.type.equals('PostgreSQL')}">selected="selected"</c:if>>PostgreSQL</option>
	  				</select>
	  				
	  				<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="entity.alias" id="alias" type="text" value="${entity.alias}" placeholder="To better identify the resource" />
					<span class="help-block"><em><small>Example: DBMS Project X</small></em></span>
					
					<label class="text-info" for="user">User:</label>
					<input name="entity.user" id="user" type="text" value="${entity.user}" placeholder="Database username" />
					
					<label class="text-info" for="port">Port:</label>
					<input class="input-small" name="entity.port" id="port" type="text" value="${entity.port}" placeholder="e.g. 3604" />							
					<span class="help-block"><em><small>Enter the database port.</small></em></span>
					
					<label class="text-info" for="password">Password:</label>
					<input name="entity.password" id="password" type="password" value="${entity.password}" /> <br>
					<input name="confirmPassword" id="confirmPassword" type="password" placeholder="Confirm the password" />
					
					<label class="text-info" for="description">Description:</label>	  							
 					<textarea name="entity.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;">${entity.description}</textarea>
					
					<label class="text-info" for="recordDate">Record Date:</label>
					<input class="input-small" name="entity.recordDate" id="recordDate" type="text" readonly="readonly" value="${current_date}" />						
					
					<div class="form-actions">
						<button type="submit" class="btn btn-success">Save</button>
						<a class="btn btn-danger" href="<c:url value="/dbmss"/>" onclick="return confirm('Are you sure want to cancel the registration?');">Cancel</a>
					</div>					  	
	  			</fieldset>
		 	</form>
	 	</div> <!-- /container -->
		
		<%@include file="/static/footer.jsp"%>
	 	<%@include file="/static/javascript_footer.jsp"%>
	</body>
</html>