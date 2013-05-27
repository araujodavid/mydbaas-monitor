<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="author" content="David Araújo"/>
		<meta name="reply-to" content="araujodavid@lia.ufc.br"/>
		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">		

		<title>MyDBaaSMonitor - Edit Host</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container" style="margin-top:70px;">					
			<form method="post" action="${pageContext.servletContext.contextPath}/hosts/update">
				<fieldset>
						<legend>
							<strong>Editing Host</strong>							
						</legend>
						
						<c:if test="${errors != null}">							
							<c:forEach var="error" items="${errors}">
								<div class="alert alert-error">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<strong>${error.category}!</strong> ${error.message}
								</div>
			  				</c:forEach>			  				
		  				</c:if>	  				
		  				
		  				<div align="right">
		  					<a href="#myModalPassword" data-toggle="modal"><i class="icon-lock"></i> Change password or key access</a>
		  				</div>
		  						  				
		  				<input name="host.id" id="id" type="hidden" value="${host.id}" />
		  				
		  				<label class="text-info" for="dbaas">Environment DBaaS:</label>
		  				<select id="dbaas" name="host.environment.id" style="width:284px;">
		  					<c:forEach var="dbaas" items="${availableDBaaS}">
								<option value="${dbaas.id}" <c:if test="${host.environment.id == dbaas.id}">selected="selected"</c:if> >
									${dbaas.alias}
								</option>
			  				</c:forEach>
		  				</select>
		  				
		  				<label class="text-info" for="alias">Alias:</label>
						<input class="input-xlarge" name="host.alias" id="alias" type="text" value="${host.alias}" placeholder="To better identify the resource" />
						<span class="help-block"><em><small>Example: VM Project X</small></em></span>
									   
				   		<label class="text-info" for="address">Address:</label>
						<input class="input-xlarge" name="host.address" id="address" type="text" value="${host.address}" placeholder="Access address" />
						<span class="help-block"><em><small>Example: 127.0.0.1</small></em></span>
						
						<label class="text-info" for="user">Username:</label>
						<input name="host.user" id="user" type="text" value="${host.user}"  />				
					  	<span class="help-block"><em><small>Make sure that the user is <strong>root</strong>.</small></em></span>
						
						<label class="text-info" for="port">Port:</label>
						<input class="input-small" name="host.port" id="port" type="text" value="${host.port}" placeholder="e.g. 22" />							
						<span class="help-block"><em><small>Enter the <strong>SSH</strong> port.</small></em></span>
						
						<label class="checkbox text-info">
			  				<input name="host.status" id="status" value=true type="checkbox" <c:if test="${host.status == true}">checked="checked"</c:if>> Status
			  			</label><br>
						
						<label class="text-info" for="description">Description:</label>	  							
 						<textarea name="host.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;" >${host.description}</textarea>						
						
						<div class="form-actions">
							<button type="submit" class="btn btn-success">Save changes</button>
							<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/hosts/list" onclick="return confirm('Are you sure want to cancel the update?');">Cancel</a>
						</div>
					  	
	  			</fieldset>
		 	</form>		 	
	 	</div> <!-- /container -->
	 	
	 	<!-- Modal Password -->
 		<form method="post" action="${pageContext.servletContext.contextPath}/hosts/password" id="myModalPassword" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel"><img src="/mydbaasmonitor/img/keys.png"> Password</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>			
	  				<input name="host.id" id="id" type="hidden" value="${host.id}" />
	  				
	  				<label class="text-info" for="password">Password:</label>
					<input name="host.password" id="password" type="password" value="${host.password}" placeholder="Root password" /> <br>
					<input name="confirmPassword" id="confirmPassword" type="password" placeholder="Confirm the password" />
					
					<label class="checkbox">
   						<input type="checkbox">Show password
 					</label>				  	
  				</fieldset>		 		
  			</div>
  			<div class="modal-footer">
  				<button type="submit" class="btn btn-success">Save changes</button>
  				<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>    			
  			</div>
 		</form>
	 	
	 	<%@include file="/static/footer.jsp"%>
		<%@include file="/static/javascript_footer.jsp"%>pt>
	</body>
</html>