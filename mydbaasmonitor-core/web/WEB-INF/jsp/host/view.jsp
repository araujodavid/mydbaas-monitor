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

		<title>MyDBaaSMonitor - Host: ${host.alias}</title>
	</head>
	<body>
		<%@include file="/static/menu.jsp"%>

		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3">
        			<legend>
						<div align="left" style="margin-bottom:10px;">
							<a class="btn btn-inverse" href="#myModalNewMachine" data-toggle="modal" title="To register a new Machine."><i class="icon-plus icon-white"></i> Virtual Machine</a>
	        			</div>
        			</legend>

        			<i class="icon-list" style="margin-right:5px; margin-bottom:10px;"></i><strong>List of Virtual Machines:</strong> 

			            <div class="accordion" id="accordion2">
			            	<c:forEach items="${host.machines}" var="machine">
	  							<div class="accordion-group">
	    							<div class="accordion-heading">
	      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapse${machine.id}">
	        								<i class="icon-list-alt" style="margin-right:6px;"></i>${machine.alias}
	     	 							</a>
	    							</div>
	    							<div id="collapse${machine.id}" class="accordion-body collapse">
    									<div class="accordion-inner">
    										 <address style="margin-bottom:5px;">
    										 	<strong>Username:</strong> ${machine.address}<br>
				     							<a class="muted" href="<c:url value="/vms/view/${machine.id}"/>"><i class="icon-pencil" style="margin-right:3px;"></i>About</a>
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
        		
        			<legend><img src="/mydbaasmonitor/img/display.png"> Host Information</legend>
        			<div class="row-fluid">
		                <div class="span4">
		                    <address>		                    	
		                    	<strong>Monitoring Status:</strong><br> 
		                    	<c:choose>
     								<c:when test="${host.status == true}">
      									<span class="label label-success">Active</span>
		        					</c:when>
		        					<c:otherwise>
      									<span class="label label-important">Inactive</span>
      								</c:otherwise>
     							</c:choose><br><br>	                    
		                    	
		                    	<strong>Environment:</strong> <a href="<c:url value="/dbaas/view/${host.environment.id}"/>" title="Link to view the machine environment.">${host.environment.alias}</a><br>
		  						<strong>Alias:</strong> <info class="muted">${host.alias}</info><br>
							  	<strong>Address:</strong> <info class="muted">${host.address}</info><br>							  	
							  	<strong>SSH Port:</strong> <info class="muted">${host.port}</info><br>
							  	<strong>Username:</strong> <info class="muted">${host.user}</info><br>
							  	<strong>Record Date:</strong> <info class="muted">${host.recordDate}</info><br>
							  	<strong>Description:</strong> <info class="muted">${host.description}</info><br><br>
							  	<a class="btn btn-success" href="<c:url value="/hosts/edit/${host.id}"/>" title="This button updates the information to access the host."><i class="icon-pencil"></i> Edit</a>
							  	<a class="btn btn-warning" href="<c:url value="/hosts/list"/>" title="This button updates the information about the resources of the host." onclick="return confirm('Are you sure want to update the information about the resources?');"><i class="icon-wrench"></i> Update</a>
							  	<a class="btn btn-danger" href="<c:url value="/hosts/list"/>" title="This button deletes the registry of the host." onclick="return confirm('Are you sure want to delete the record?');"><i class="icon-remove"></i> Delete</a>
							</address> 
		                </div><!--/informationAccess-->
                
		                <div class="span4">
		                	<div class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#tab1" data-toggle="tab">Geral</a></li>
							  	</ul>
							  	<div class="tab-content">
							    	<div class="tab-pane active" id="tab1">
							      		<address>
					  						<strong>Name:</strong> <info class="muted">${host.information.hostInfoName}</info><br>
					  						<strong>Hypervisor:</strong> <info class="muted">${host.information.hostInfoHypervisor}</info><br>
					  						<strong>Memory:</strong> <info class="muted">${host.information.hostInfoMemory}</info><br>
					  						<strong>Cores:</strong> <info class="muted">${host.information.hostInfoCores}</info><br>
					  						<strong>CPUs:</strong> <info class="muted">${host.information.hostInfoCpus}</info><br>
					  						<strong>Frequency:</strong> <info class="muted">${host.information.hostInfoMhz}</info><br>
					  						<strong>Model:</strong> <info class="muted">${host.information.hostInfoModel}</info><br>											
										</address> 
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
		
		<!-- Modal New Virtual Machine -->
 		<form method="post" action="<c:url value="/vms/add"/>" id="myModalNewMachine" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel"><img src="/mydbaasmonitor/img/new.png"> New Virtual Machine</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>			
	  				<input name="virtualMachine.host.id" id="host" type="hidden" value="${host.id}" />
	  				
	  				<label class="text-info" for="dbaas">Environment DBaaS:</label>
	  				<select id="dbaas" name="virtualMachine.environment.id" style="width:284px;">
	  					<option value="0" selected="selected">Select one</option>
	  					<c:forEach var="dbaas" items="${availableDBaaS}">
							<option value="${dbaas.id}">${dbaas.alias}</option>
		  				</c:forEach>
	  				</select>
	  				<c:if test="${availableDBaaS == null}">
	  					<span class="help-block"><em><small>There is no registered environments. Register a new <a href="<c:url value="/dbaas/new" />">here</a>.</small></em></span>
	  				</c:if>
	  				
	  				<label class="text-info" for="hosts">Host:</label>
	  				<input class="input-small" type="text" readonly="readonly" value="${host.alias}" />
	  				
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
 		
 	<%@include file="/static/footer.jsp"%>	
	  	
    <script src="http://code.jquery.com/jquery-latest.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.js" type="text/javascript"></script>
    <script src="http://code.highcharts.com/stock/highstock.js" type="text/javascript"></script>
    <script src="http://code.highcharts.com/highcharts.js" type="text/javascript"></script>
    <script src="http://code.highcharts.com/modules/exporting.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/js/vms_view.js" type="text/javascript"></script>
	</body>
</html>
