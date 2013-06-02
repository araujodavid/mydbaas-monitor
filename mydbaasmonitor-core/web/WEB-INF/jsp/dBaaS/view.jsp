<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Content-Language" content="pt-br" />
		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
		<style type="text/css">
      		body {
        		padding-top: 60px;
        		padding-bottom: 40px;
      		}
      		.sidebar-nav {
        		padding: 9px 0;
      		}
    	</style>
    	
    	<link href="${pageContext.servletContext.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">

		<title>MyDBaaSMonitor - DBaaS: ${dBaaS.alias}</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3">        		
        			<legend>
						<div align="left" style="margin-bottom:10px;">
							<a class="btn btn-inverse" href="#myModalNewHost" data-toggle="modal" title="To create a new host."><i class="icon-plus icon-white"></i> Host</a>
							<a class="btn btn-inverse" href="#myModalNewVM" data-toggle="modal" title="To create a new virtual machine."><i class="icon-plus icon-white"></i> Virtual Machine</a>
	        			</div>
        			</legend>
        			
        			<i class="icon-list" style="margin-right:5px; margin-bottom:10px;"></i><strong>List of Hosts:</strong>        		
		            <c:if test="${dBaaS.hosts.isEmpty() || dBaaS.hosts == null}">
						<div class="alert" style="margin-top:5px;">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							There are no <strong>hosts</strong>.
						</div>
					</c:if>
		            <div class="accordion" id="accordion2">
		            	<c:forEach items="${dBaaS.hosts}" var="host">
  							<div class="accordion-group">
    							<div class="accordion-heading">
      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapseHost${host.id}">
        								<i class="icon-home" style="margin-right:6px;"></i>${host.alias}
     	 							</a>
    							</div>
    							<div id="collapseHost${host.id}" class="accordion-body collapse">
   									<div class="accordion-inner">
   										 <address style="margin-bottom:5px;">
   										 	<strong>Username:</strong> ${host.user}<br>
   										 	<strong>Address:</strong> ${host.address}<br>
   										 	<strong>Port:</strong> ${host.port}<br>
   										 	<strong>Record Date:</strong> ${host.recordDate}<br>
						  					<strong>Description:</strong> ${host.description}<br>
						  					<strong>Monitoring Status:</strong><br> 
					                    	<c:choose>
			     								<c:when test="${host.status == true}">
			      									<span class="label label-success">Active</span><br><br>
					        					</c:when>
					        					<c:otherwise>
			      									<span class="label label-important">Inactive</span><br><br>
			      								</c:otherwise>
			     							</c:choose>
			     							<a class="muted" href="<c:url value="/hosts/view/${host.id}"/>"><i class="icon-wrench" style="margin-right:3px;"></i>About</a>
   										 </address>       									
     								</div>   								
    							</div>
  							</div>
 						</c:forEach>
					</div>
					
        			<i class="icon-list" style="margin-right:5px; margin-bottom:10px;"></i><strong>List of Virtual Machines:</strong>        		
		            <c:if test="${dBaaS.machines.isEmpty() || dBaaS.machines == null}">
						<div class="alert" style="margin-top:5px;">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							There are no <strong>virtual machines</strong>.
						</div>
					</c:if>
		            <div class="accordion" id="accordion2">
		            	<c:forEach items="${dBaaS.machines}" var="machine">
  							<div class="accordion-group">
    							<div class="accordion-heading">
      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapseVM${machine.id}">
        								<i class="icon-hdd" style="margin-right:6px;"></i>${machine.alias}
     	 							</a>
    							</div>
    							<div id="collapseVM${machine.id}" class="accordion-body collapse">
   									<div class="accordion-inner">
   										 <address style="margin-bottom:5px;">
   										 	<strong>Username:</strong> ${machine.user}<br>
   										 	<strong>Address:</strong> ${machine.address}<br>
   										 	<strong>Port:</strong> ${machine.port}<br>
   										 	<strong>Record Date:</strong> ${machine.recordDate}<br>
						  					<strong>Description:</strong> ${machine.description}<br>
						  					<strong>Monitoring Status:</strong><br> 
					                    	<c:choose>
			     								<c:when test="${machine.status == true}">
			      									<span class="label label-success">Active</span><br><br>
					        					</c:when>
					        					<c:otherwise>
			      									<span class="label label-important">Inactive</span><br><br>
			      								</c:otherwise>
			     							</c:choose>
			     							<a class="muted" href="<c:url value="/vms/view/${machine.id}"/>"><i class="icon-wrench" style="margin-right:3px;"></i>About</a>
   										 </address>       									
     								</div>   								
    							</div>
  							</div>
 						</c:forEach>
					</div>					
					
					<i class="icon-list" style="margin-right:5px; margin-bottom:10px;"></i><strong>List of Database Management Systems:</strong>        		
		            <c:if test="${dBaaS.dbmss.isEmpty() || dBaaS.dbmss == null}">
						<div class="alert" style="margin-top:5px;">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							There are no <strong>database management systems</strong>.
						</div>
					</c:if>
		            <div class="accordion" id="accordion2">
		            	<c:forEach items="${dBaaS.dbmss}" var="dbms">
  							<div class="accordion-group">
    							<div class="accordion-heading">
      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapseDBMS${dbms.id}">
        								<i class="icon-folder-close" style="margin-right:6px;"></i>${dbms.alias}
     	 							</a>
    							</div>
    							<div id="collapseDBMS${dbms.id}" class="accordion-body collapse">
   									<div class="accordion-inner">
   										 <address style="margin-bottom:5px;">
   										 	<strong>Username:</strong> ${dbms.user}<br>
   										 	<strong>Port:</strong> ${dbms.port}<br>
   										 	<strong>Type:</strong> ${dbms.type}<br>
   										 	<strong>Record Date:</strong> ${dbms.recordDate}<br>
						  					<strong>Description:</strong> ${dbms.description}<br>
						  					<strong>Monitoring Status:</strong><br> 
					                    	<c:choose>
			     								<c:when test="${dbms.status == true}">
			      									<span class="label label-success">Active</span><br><br>
					        					</c:when>
					        					<c:otherwise>
			      									<span class="label label-important">Inactive</span><br><br>
			      								</c:otherwise>
			     							</c:choose>
			     							<a class="muted" href="<c:url value="/dbmss/view/${dbms.id}"/>"><i class="icon-wrench" style="margin-right:3px;"></i>About</a>
   										 </address>       									
     								</div>   								
    							</div>
  							</div>
 						</c:forEach>
					</div>
					
					<i class="icon-list" style="margin-right:5px; margin-bottom:10px;"></i><strong>List of Databases:</strong>        		
		            <c:if test="${dBaaS.databases.isEmpty() || dBaaS.databases == null}">
						<div class="alert" style="margin-top:5px;">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							There are no <strong>databases</strong>.
						</div>
					</c:if>
		            <div class="accordion" id="accordion2">
		            	<c:forEach items="${dBaaS.databases}" var="database">
  							<div class="accordion-group">
    							<div class="accordion-heading">
      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapseDB${database.id}">
        								<i class="icon-list-alt" style="margin-right:6px;"></i>${database.alias}
     	 							</a>
    							</div>
    							<div id="collapseDB${database.id}" class="accordion-body collapse">
   									<div class="accordion-inner">
   										 <address style="margin-bottom:5px;">
   										 	<strong>Schema:</strong> ${database.name}<br>
   										 	<strong>Record Date:</strong> ${database.recordDate}<br>
						  					<strong>Description:</strong> ${database.description}<br>
						  					<strong>Monitoring Status:</strong><br> 
					                    	<c:choose>
			     								<c:when test="${database.status == true}">
			      									<span class="label label-success">Active</span><br><br>
					        					</c:when>
					        					<c:otherwise>
			      									<span class="label label-important">Inactive</span><br><br>
			      								</c:otherwise>
			     							</c:choose>
			     							<a class="muted" href="<c:url value="/databases/view/${database.id}"/>"><i class="icon-wrench" style="margin-right:3px;"></i>About</a>
   										 </address>       									
     								</div>   								
    							</div>
  							</div>
 						</c:forEach>
					</div>        			
        		</div><!--/span-->
        		
        		<div class="span9">
        			<c:if test="${notice != null}">							
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${notice}
						</div>		  				
	  				</c:if>
        		
        			<legend><img src="/mydbaasmonitor/img/cloud.png"> Database-as-a-Service Information</legend>
        			<div class="row-fluid">
		                <div class="span4">
		                    <address>		                    	
		                    	<h3>Alias:</h3> <h4 class="muted">${dBaaS.alias}</h4>
							  	<h3>Record Date:</h3> <h4 class="muted">${dBaaS.recordDate}</h4>
							  	<h3>Description:</h3> <h4 class="muted">${dBaaS.description}</h4>                    	
							</address> 
		                </div><!--/informationAccess-->
                
		                <div class="span4">
		                	
		                </div><!--/informationTab-->		                
		            </div><!--/row-->
		            
		            <div class="hero">
                		<legend><img src="/mydbaasmonitor/img/charts.png"> Dashboard</legend>
            		</div><!--/dashboard-->
            		            		       
        		</div><!--/span-->       		
    		</div><!--/row-->
		</div><!--/.fluid-container-->
		
		<!-- Modal New Virtual Machine -->
 		<form method="post" action="<c:url value="/vms/add"/>" id="myModalNewVM" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel"><img src="/mydbaasmonitor/img/new.png"> New Virtual Machine</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>			
	  				<input name="virtualMachine.environment.id" id="dbaas" type="hidden" value="${dBaaS.id}" />
	  				<label class="text-info" for="hosts">Environment DBaaS:</label>
	  				<input class="input-xlarge" type="text" readonly="readonly" value="${dBaaS.alias}" />
	  				
	  				<label class="text-info" for="hosts">Host:</label>
	  				<select id="hosts" name="virtualMachine.host.id" style="width:284px;">
	  					<option value="0" selected="selected">Without Host</option>
	  					<c:forEach var="host" items="${availableHosts}">
							<option value="${host.id}">${host.alias}</option>
		  				</c:forEach>
	  				</select>
	  				<c:if test="${availableHosts == null || availableHosts.isEmpty()}">
	  					<span class="help-block"><em><small class="text-error">There is no registered hosts. Create a new <a href="<c:url value="/hosts/new" />">here</a>.</small></em></span>
	  				</c:if>
	  				
	  				<div id="div_identifier_host">
		  				<label class="text-info" for="alias">Identifier in Host:</label>
						<input class="input-xlarge" name="virtualMachine.identifierHost" id="identifier_host" type="text" placeholder="Identifier in the hypervisor" />
						<span class="help-block"><em><small>Enter only if a Host was selected.</small></em></span>
						<span class="help-block"><em><small>If the host is using the KVM hypervisor you must inform the ID of the virtual machine.</small></em></span>
	  				</div>
	  				
	  				<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="virtualMachine.alias" id="alias" type="text" placeholder="To better identify the resource" required />
					<span class="help-block"><em><small>Example: VM Project X</small></em></span>
								   
			   		<label class="text-info" for="address">Address:</label>
					<input class="input-xlarge" name="virtualMachine.address" id="address" type="text" placeholder="Access address" required />
					<span class="help-block"><em><small>Example: 127.0.0.1</small></em></span>
					
					<label class="text-info" for="user">Username:</label>
					<input name="virtualMachine.user" id="user" type="text" required />				
				  	<span class="help-block"><em><small>Make sure that the user is <strong>root</strong>.</small></em></span>
					
					<label class="text-info" for="port">Port:</label>
					<input class="input-small" name="virtualMachine.port" id="port" type="text" placeholder="e.g. 22" required />							
					<span class="help-block"><em><small>Enter the <strong>SSH</strong> port.</small></em></span>
					
					<label class="text-info" for="password">Password:</label>
					<input name="virtualMachine.password" id="password" type="password" placeholder="Root password" required /> <br>
					<input name="confirmPassword" id="confirmPassword" type="password" placeholder="Confirm the password" required />					    
			
					<label class="text-info" for="key">Key:</label>
					<div class="input-append">
 							<input class="input-medium" name="virtualMachine.key" id="key" type="text" readonly="readonly" />
 							<button class="btn btn-primary" type="button">Send</button>
					</div>
					<span class="help-block"><em><small>If you need an access <strong>key</strong>, upload the file instead of entering the password.</small></em></span>
					
					<label class="text-info" for="description">Description:</label>	  							
					<textarea name="virtualMachine.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;" ></textarea>
					
					<label class="text-info" for="recordDate">Record Date:</label>
					<input class="input-small" name="virtualMachine.recordDate" id="recordDate" type="text" readonly="readonly" value="${current_date}" />
  				</fieldset>		 		
  			</div>
  			<div class="modal-footer">
  				<button type="submit" class="btn btn-success">Save changes</button>
  				<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>    			
  			</div>
 		</form>
 		
 		<!-- Modal New Host -->
 		<form method="post" action="<c:url value="/hosts/add"/>" id="myModalNewHost" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel"><img src="/mydbaasmonitor/img/new.png"> New Host</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>			
	  				<input name="host.environment.id" id="dbaas" type="hidden" value="${dBaaS.id}" />
	  				<label class="text-info" for="hosts">Environment DBaaS:</label>
	  				<input class="input-xlarge" type="text" readonly="readonly" value="${dBaaS.alias}" />
	  				
	  				<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="host.alias" id="alias" type="text" placeholder="To better identify the resource" required />
					<span class="help-block"><em><small>Example: Cluster Project X</small></em></span>
								   
			   		<label class="text-info" for="address">Address:</label>
					<input class="input-xlarge" name="host.address" id="address" type="text" placeholder="Access address" required />
					<span class="help-block"><em><small>Example: 127.0.0.1</small></em></span>
					
					<label class="text-info" for="user">Username:</label>
					<input name="host.user" id="user" type="text" required />				
				  	<span class="help-block"><em><small>Make sure that the user is <strong>root</strong>.</small></em></span>
					
					<label class="text-info" for="port">Port:</label>
					<input class="input-small" name="host.port" id="port" type="text" placeholder="e.g. 22" required />							
					<span class="help-block"><em><small>Enter the <strong>SSH</strong> port.</small></em></span>
					
					<label class="text-info" for="password">Password:</label>
					<input name="host.password" id="password" type="password" placeholder="Root password" required /> <br>
					<input name="confirmPassword" id="confirmPassword" type="password" placeholder="Confirm the password" required />
					
					<label class="text-info" for="description">Description:</label>	  							
					<textarea name="host.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;"></textarea>
					
					<label class="text-info" for="recordDate">Record Date:</label>
					<input class="input-small" name="host.recordDate" id="recordDate" type="text" readonly="readonly" value="${current_date}" />
  				</fieldset>		 		
  			</div>
  			<div class="modal-footer">
  				<button type="submit" class="btn btn-success">Save changes</button>
  				<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>    			
  			</div>
 		</form> 		
			
	  	<%@include file="/static/javascript_footer.jsp"%>
	  	<%@include file="/static/footer.jsp"%>
	</body>
</html>