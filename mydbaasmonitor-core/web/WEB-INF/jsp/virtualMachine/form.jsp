<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="author" content="David Araújo"/>
		<meta name="reply-to" content="araujodavid@lia.ufc.br"/>
		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>
    	<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>

		<title>MyDBaaSMonitor</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container" style="margin-top:70px;">		
			<form method="post" action="${pageContext.servletContext.contextPath}/vms/add">
				<fieldset>
						<legend>New Machine</legend>
						
						<c:if test="${errors != null}">							
							<c:forEach var="error" items="${errors}">
								<div class="alert alert-error">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<strong>${error.category}!</strong> ${error.message}
								</div>
			  				</c:forEach>			  				
		  				</c:if>
		  				
		  				<label class="text-info" for="alias">Alias:</label>
						<input class="input-xlarge" name="virtualMachine.alias" id="alias" type="text" value="${virtualMachine.alias}" placeholder="To better identify the resource" />
						<span class="help-block"><em><small>Example: VM Project X</small></em></span>
									   
				   		<label class="text-info" for="host">Host:</label>
						<input class="input-xlarge" name="virtualMachine.host" id="host" type="text" value="${virtualMachine.host}" placeholder="Access address" />
						<span class="help-block"><em><small>Example: 127.0.0.1</small></em></span>
						
						<label class="text-info" for="user">Username:</label>
						<input name="virtualMachine.user" id="user" type="text" value="${virtualMachine.user}"  />				
					  	<span class="help-block"><em><small>Make sure that the user is <strong>root</strong>.</small></em></span>
						
						<label class="text-info" for="port">Port:</label>
						<input class="input-small" name="virtualMachine.port" id="port" type="text" value="${virtualMachine.port}" placeholder="e.g. 22" />							
						<span class="help-block"><em><small>Enter the <strong>SSH</strong> port.</small></em></span>
						
						<label class="text-info" for="password">Password:</label>
						<input name="virtualMachine.password" id="password" type="password" value="${virtualMachine.password}" placeholder="Root password" /> <br>
						<input name="confirmPassword" id="confirmPassword" type="password" placeholder="Confirm the password" />					    
				
						<label class="text-info" for="key">Key:</label>
						<div class="input-append">
  							<input class="input-medium" name="virtualMachine.key" id="key" type="text" readonly="readonly" value="${virtualMachine.key}" />
  							<button class="btn btn-primary" type="button">Send</button>
						</div>
						<span class="help-block"><em><small>If you need an access <strong>key</strong>, upload the file instead of entering the password.</small></em></span>
						
						<label class="text-info" for="description">Description:</label>	  							
 						<textarea name="virtualMachine.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;" >${virtualMachine.description}</textarea>
						
						<label class="text-info" for="recordDate">Record Date:</label>
						<input class="input-small" name="virtualMachine.recordDate" id="recordDate" type="text" readonly="readonly" value="${current_date}" />						
						
						<div class="form-actions">
							<button type="submit" class="btn btn-success">Save</button>
							<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/vms/list" onclick="return confirm('Are you sure want to cancel the registration?');">Cancel</a>
						</div>
					  	
	  			</fieldset>
		 	</form>
	 	</div> <!-- /container -->
	 	
	 	<%@include file="/static/footer.jsp"%>
	 	
	</body>
</html>