<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="author" content="David Araújo"/>
		<meta name="reply-to" content="araujodavid@lia.ufc.br"/>
		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">		

		<title>MyDBaaSMonitor - Edit Virtual Machine</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container" style="margin-top:70px;">					
			<form method="post" action="${pageContext.servletContext.contextPath}/vms/update">
				<fieldset>
						<legend>
							<strong>Editing Virtual Machine</strong>							
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
		  					<a href="#myModalPassword" data-toggle="modal"><i class="icon-lock"></i> Change password</a>
		  				</div>
		  						  				
		  				<input name="virtualMachine.id" id="id" type="hidden" value="${virtualMachine.id}" />
		  				
		  				<label class="text-info" for="dbaas">Environment DBaaS:</label>
		  				<select id="dbaas" name="virtualMachine.environment.id" style="width:284px;">
		  					<c:forEach var="dbaas" items="${availableDBaaS}">
								<option value="${dbaas.id}" <c:if test="${virtualMachine.environment.id == dbaas.id}">selected="selected"</c:if> >
									${dbaas.alias}
								</option>
			  				</c:forEach>
		  				</select>
		  				
		  				<label class="text-info" for="host">Host:</label>
		  				<select id="hosts" name="virtualMachine.host.id" style="width:284px;">
		  					<c:if test="${virtualMachine.host.id == 0}">
			  					<option value="0" selected="selected">Without Host</option>
			  					<c:forEach var="host" items="${availableHosts}">
									<option value="${host.id}">${host.alias}</option>
			  					</c:forEach>
			  				</c:if>
		  					<c:if test="${virtualMachine.host.id > 0}">
			  					<option value="0">Without Host</option>
			  					<c:forEach var="host" items="${availableHosts}">
			  						<option value="${host.id}" <c:if test="${virtualMachine.host.id == host.id}">selected="selected"</c:if> >
										${host.alias}
									</option>
			  					</c:forEach>
			  				</c:if>	  				
		  				</select>

						<div id="div_identifier_host">
			  				<label class="text-info" for="alias">Identifier in Host:</label>
							<input class="input-xlarge" name="virtualMachine.identifierHost" id="identifier_host" type="text" value="${virtualMachine.identifierHost}" placeholder="Identifier in the hypervisor" />
							<span class="help-block"><em><small>If the host is using the KVM hypervisor you must inform the ID of the virtual machine.</small></em></span>
		  				</div>
		  				
		  				<label class="text-info" for="alias">Alias:</label>
						<input class="input-xlarge" name="virtualMachine.alias" id="alias" type="text" value="${virtualMachine.alias}" placeholder="To better identify the resource" />
						<span class="help-block"><em><small>Example: VM Project X</small></em></span>
									   
				   		<label class="text-info" for="address">Address:</label>
						<input class="input-xlarge" name="virtualMachine.address" id="address" type="text" value="${virtualMachine.address}" placeholder="Access address" />
						<span class="help-block"><em><small>Example: 127.0.0.1</small></em></span>
						
						<label class="text-info" for="user">Username:</label>
						<input name="virtualMachine.user" id="user" type="text" value="${virtualMachine.user}"  />				
					  	<span class="help-block"><em><small>Make sure that the user is <strong>root</strong>.</small></em></span>
						
						<label class="text-info" for="port">Port:</label>
						<input class="input-small" name="virtualMachine.port" id="port" type="text" value="${virtualMachine.port}" placeholder="e.g. 22" />							
						<span class="help-block"><em><small>Enter the <strong>SSH</strong> port.</small></em></span>
						
						<label class="text-info" for="description">Description:</label>	  							
 						<textarea name="virtualMachine.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;" >${virtualMachine.description}</textarea>						
						
						<div class="form-actions">
							<button type="submit" class="btn btn-success">Save changes</button>
							<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/vms/list" onclick="return confirm('Are you sure want to cancel the update?');">Cancel</a>
						</div>
					  	
	  			</fieldset>
		 	</form>		 	
	 	</div> <!-- /container -->
	 	
	 	<!-- Modal Password -->
 		<form method="post" action="${pageContext.servletContext.contextPath}/vms/machine/password" id="myModalPassword" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel"><img src="/mydbaasmonitor/img/keys.png"> Password</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>			
	  				<input name="virtualMachine.id" id="id" type="hidden" value="${virtualMachine.id}" />
	  				
	  				<label class="text-info" for="password">Password:</label>
					<input name="virtualMachine.password" id="password" type="password" value="${virtualMachine.password}" placeholder="Root password" /> <br>
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
 		
 		<!-- Modal Key -->
 		<form method="post" action="${pageContext.servletContext.contextPath}/vms/machine/key" id="myModalKey" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel"><img src="/mydbaasmonitor/img/keys.png"> Password</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>			
	  				<input name="virtualMachine.id" id="id" type="hidden" value="${virtualMachine.id}" />  								  	
  				</fieldset>		 		
  			</div>
  			<div class="modal-footer">
  				<button type="submit" class="btn btn-success">Upload</button>
  				<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>    			
  			</div>
 		</form>
	 	
	 	<%@include file="/static/footer.jsp"%>
	 	<%@include file="/static/javascript_footer.jsp"%>
    	
    	<script type="text/javascript">
    		function verifyHost(){
    			if( $("#hosts option:selected").text() == "Without Host"){
    				$("#identifier_host").val("");
    				$('#div_identifier_host').hide();    				
    			}else{
    				$('#div_identifier_host').show();
    			}
    		}
    	
	    	$(document).ready(function() {
	    		verifyHost();
	        	$("#hosts").change(function(){
	        		verifyHost();  
	    		});    		
	    	});
    	</script>
	</body>
</html>