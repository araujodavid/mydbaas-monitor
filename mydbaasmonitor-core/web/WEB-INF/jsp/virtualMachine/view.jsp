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

		<title>MyDBaaSMonitor - Machine: ${virtualMachine.alias}</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3">        		
        			<legend>
						<div align="left" style="margin-bottom:10px;">
							<a class="btn btn-inverse" href="#myModalNewDatabase" data-toggle="modal" title="To create a new database."><i class="icon-plus icon-white"></i> Database</a>
	        			</div>
        			</legend>
        			
        			<i class="icon-list" style="margin-right:5px; margin-bottom:10px;"></i><strong>List of Databases:</strong> 
        		
			            <div class="accordion" id="accordion2">
			            	<c:forEach items="${databaseList}" var="database">
	  							<div class="accordion-group">
	    							<div class="accordion-heading">
	      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapse${database.id}">
	        								<i class="icon-list-alt" style="margin-right:6px;"></i>${database.alias}
	     	 							</a>
	    							</div>
	    							<div id="collapse${database.id}" class="accordion-body collapse">
    									<div class="accordion-inner">
    										 <address style="margin-bottom:5px;">
    										 	<strong>Username:</strong> <info class="muted">${database.user}</info><br>
    										 	<strong>Port:</strong> <info class="muted">${database.port}</info><br>
    										 	<strong>Type:</strong> <info class="muted">${database.type}</info><br>
    										 	<strong>Record Date:</strong> <info class="muted">${database.recordDate}</info><br>
							  					<strong>Description:</strong> <info class="muted">${database.description}</info><br>
							  					<strong>Monitoring Status:</strong><br> 
						                    	<c:choose>
				     								<c:when test="${database.status == true}">
				      									<span class="label label-success">Active</span>
						        					</c:when>
						        					<c:otherwise>
				      									<span class="label label-important">Inactive</span>
				      								</c:otherwise>
				     							</c:choose>
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
        		
        			<legend><img src="/mydbaasmonitor/img/display.png"> Virtual Machine Information</legend>
        			<div class="row-fluid">
		                <div class="span4">
		                    <address>		                    	
		                    	<strong>Monitoring Status:</strong><br> 
		                    	<c:choose>
     								<c:when test="${virtualMachine.status == true}">
      									<span class="label label-success">Active</span>
		        					</c:when>
		        					<c:otherwise>
      									<span class="label label-important">Inactive</span>
      								</c:otherwise>
     							</c:choose><br><br>	                    
		                    	
		                    	<strong>Environment:</strong> <a href="<c:url value="/dbaas/view/${virtualMachine.environment.id}"/>" title="Link to view the machine environment.">${virtualMachine.environment.alias}</a><br>
		  						<strong>Alias:</strong> <info class="muted">${virtualMachine.alias}</info><br>
							  	<strong>Host:</strong> <info class="muted">${virtualMachine.host}</info><br>							  	
							  	<strong>SSH Port:</strong> <info class="muted">${virtualMachine.port}</info><br>
							  	<strong>Username:</strong> <info class="muted">${virtualMachine.user}</info><br>
							  	<strong>Record Date:</strong> <info class="muted">${virtualMachine.recordDate}</info><br>
							  	<strong>Description:</strong> <info class="muted">${virtualMachine.description}</info><br><br>
							  	<a class="btn btn-success" href="<c:url value="/vms/edit/${virtualMachine.id}"/>" title="This button updates the information to access the machine."><i class="icon-pencil"></i> Edit</a>
							  	<a class="btn btn-warning" href="<c:url value="/vms/list"/>" title="This button updates the information about the resources of the machine." onclick="return confirm('Are you sure want to update the information about the resources?');"><i class="icon-wrench"></i> Update</a>
							  	<a class="btn btn-danger" href="<c:url value="/vms/list"/>" title="This button deletes the registry of the machine." onclick="return confirm('Are you sure want to delete the record?');"><i class="icon-remove"></i> Delete</a>
							</address> 
		                </div><!--/informationAccess-->
                
		                <div class="span4">
		                	<div class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#tab1" data-toggle="tab">Geral</a></li>
							    	<li><a href="#tab2" data-toggle="tab">CPU</a></li>
							    	<li><a href="#tab3" data-toggle="tab">Disk</a></li>
							  	</ul>
							  	<div class="tab-content">
							    	<div class="tab-pane active" id="tab1">
							      		<address>
					  						<strong>Operating System:</strong> <info class="muted">${virtualMachine.system.operatingSystem}</info><br>
					  						<strong>Architecture:</strong> <info class="muted">${virtualMachine.system.architecture}</info><br>
					  						<strong>Kernel:</strong> <info class="muted">${virtualMachine.system.kernelName}</info><br>
					  						<strong>Version:</strong> <info class="muted">${virtualMachine.system.kernelVersion}</info><br>
					  						<strong>Memory:</strong> <info class="muted"><c:if test="${virtualMachine.system.totalMemory > 0}">${virtualMachine.system.totalMemory} GB</c:if></info><br>
					  						<strong>Swap:</strong> <info class="muted"><c:if test="${virtualMachine.system.totalSwap > 0}">${virtualMachine.system.totalSwap} GB</c:if></info><br>											
										</address> 
							    	</div>
    								<div class="tab-pane" id="tab2">
      									<address>
					  						<strong>Total CPUs:</strong> <info class="muted"><c:if test="${virtualMachine.system.totalCPUCores > 0}">${virtualMachine.system.totalCPUCores}</c:if></info><br>
					  						<strong>Physical CPUs:</strong> <info class="muted"><c:if test="${virtualMachine.system.totalCPUSockets > 0}">${virtualMachine.system.totalCPUSockets}</c:if></info><br>
					  						<strong>Cores per CPU:</strong> <info class="muted"><c:if test="${virtualMachine.system.totalCoresPerSocket > 0}">${virtualMachine.system.totalCoresPerSocket}</c:if></info><br>									
										</address> 
    								</div>
    								<div class="tab-pane" id="tab3">
      									
    								</div>
  								</div>
							</div>
		                </div><!--/informationTab-->		                
		            </div><!--/row-->
		            
		            <div class="hero">
                		<legend><img src="/mydbaasmonitor/img/charts.png"> Dashboard</legend>
            		</div><!--/dashboard-->
            		            		       
        		</div><!--/span-->       		
    		</div><!--/row-->
		</div><!--/.fluid-container-->	
		
		<!-- Modal New Database -->
 		<form method="post" action="<c:url value="/database/add"/>" id="myModalNewDatabase" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel"><img src="/mydbaasmonitor/img/new_database.png"> New Database</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>			
	  				<input name="entity.virtualMachine.id" id="id" type="hidden" value="${virtualMachine.id}" />
	  				
	  				<label class="text-info" for="type">Database Type:</label>
	  				<select id="type" name="entity.type" style="width:284px;">
	  					<option value="select" selected="selected">Select one</option>
						<option value="MySQL">MySQL</option>
						<option value="PostgreSQL">PostgreSQL</option>
	  				</select>
	  				
	  				<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="entity.alias" id="alias" type="text" placeholder="To better identify the resource" />
					<span class="help-block"><em><small>Example: Database Project X</small></em></span>
					
					<label class="text-info" for="user">User:</label>
					<input name="entity.user" id="user" type="text" placeholder="Database username" />
					
					<label class="text-info" for="port">Port:</label>
					<input class="input-small" name="entity.port" id="port" type="text" placeholder="e.g. 3604" />							
					<span class="help-block"><em><small>Enter the database port.</small></em></span>
					
					<label class="text-info" for="password">Password:</label>
					<input name="entity.password" id="password" type="password" /> <br>
					<input name="confirmPassword" id="confirmPassword" type="password" placeholder="Confirm the password" />
					
					<label class="text-info" for="description">Description:</label>	  							
 					<textarea name="entity.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;"></textarea>
					
					<label class="text-info" for="recordDate">Record Date:</label>
					<input class="input-small" name="entity.recordDate" id="recordDate" type="text" readonly="readonly" value="${current_date}" />			  	
  				</fieldset>		 		
  			</div>
  			<div class="modal-footer">
  				<button type="submit" class="btn btn-success">Save changes</button>
  				<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>    			
  			</div>
 		</form>
 		
 		<%@include file="/static/footer.jsp"%>	
	  	
	  	<script src="http://code.jquery.com/jquery-latest.js"></script>
    	<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
	</body>
</html>