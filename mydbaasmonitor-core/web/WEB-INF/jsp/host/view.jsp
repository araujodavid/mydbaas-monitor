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
      		.dynamic_chart{
      			height: 250px; 
      			width: 500px
      		}
      		.centered{
      			width:840px; 
      			margin-left:-420px;
      		}
      		.centered_dynamic_chart{
      			margin:20px; 
      			width:800px;
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
						<c:if test="${host.machines.isEmpty()}">
							<div class="alert" style="margin-top:5px;">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								There are no <strong>virtual machines</strong>.
							</div>
						</c:if>
			            <div class="accordion" id="accordion2">
			            	<c:forEach items="${host.machines}" var="machine">
	  							<div class="accordion-group">
	    							<div class="accordion-heading">
	      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapse${machine.id}">
	        								<i class="icon-hdd" style="margin-right:6px;"></i>${machine.alias}
	     	 							</a>
	    							</div>
	    							<div id="collapse${machine.id}" class="accordion-body collapse">
    									<div class="accordion-inner">
    										 <address style="margin-bottom:5px;">
    										 	<strong>Username:</strong> ${machine.user}<br>
    										 	<strong>Address:</strong> ${machine.address}<br>
    										 	<strong>Port:</strong> ${machine.port}<br>
    										 	<c:if test="${machine.identifierHost != null}">
    										 		<strong>Identificer in Host:</strong> ${machine.identifierHost}<br>
    										 	</c:if>
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
        		</div><!--/span-->

        		<div class="span9">
        			<c:if test="${notice != null}">							
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${notice}
						</div>		  				
	  				</c:if>
	  				
	  				<c:if test="${agentNotice != null}">							
						<c:choose>
							<c:when test="${status == true}">
								<div class="alert alert-success">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									${agentNotice}
								</div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-error">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									${agentNotice}
								</div>
							</c:otherwise>
						</c:choose>	  				
	  				</c:if>
        		
        			<legend><img src="/mydbaasmonitor/img/display.png"> Host Information</legend>
        			<div class="row-fluid">
		                <div class="span4">
		                    <address>
		                    	<input id="resource_id_chart" type="hidden" value="${host.id}" />	
		                    		                    	
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
							  	<a class="btn btn-warning" href="<c:url value="/hosts/edit/${host.id}"/>" title="This button updates the information to access the host."><i class="icon-pencil"></i> Edit</a>
							  	<a class="btn btn-success" href="<c:url value="/agent/host/${host.id}"/>" title="This button updates the information about the monitoring agent."><i class="icon-repeat"></i> Start Monitor</a>
							  	<c:if test="${host.status == true}">
							  		<a class="btn btn-danger" href="<c:url value="/agent/stop/host/${host.id}"/>" title="This button stops the monitoring agent." onclick="return confirm('Are you sure you want to stop the monitoring?');"><i class="icon-remove"></i> Stop Monitor</a>
								</c:if>
							</address> 
		                </div><!--/informationAccess-->
                
		                <div class="span4">
		                	<c:if test="${host.information.hostInfoName != null && host.information.hostInfoMemory > 0}">
			                	<div class="tabbable">
									<ul class="nav nav-tabs">
										<li class="active"><a href="#tab1" data-toggle="tab">Geral</a></li>
								  	</ul>
								  	<div class="tab-content">
								    	<div class="tab-pane active" id="tab1">
								      		<address>
						  						<strong>Name:</strong> <info class="muted">${host.information.hostInfoName}</info><br>
						  						<strong>Hypervisor:</strong> <info class="muted">${host.information.hostInfoHypervisor}</info><br>
						  						<strong>Memory:</strong> <info class="muted"><c:if test="${host.information.hostInfoMemory > 0}">${host.information.hostInfoMemory} GB</c:if></info><br>
						  						<strong>Cores:</strong> <info class="muted"><c:if test="${host.information.hostInfoCores > 0}">${host.information.hostInfoCores}</c:if></info><br>
						  						<strong>CPUs:</strong> <info class="muted"><c:if test="${host.information.hostInfoCpus > 0}">${host.information.hostInfoCpus}</c:if></info><br>
						  						<strong>Frequency:</strong> <info class="muted"><c:if test="${host.information.hostInfoMhz > 0}">${host.information.hostInfoMhz} Mhz</c:if></info><br>
						  						<strong>Model:</strong> <info class="muted">${host.information.hostInfoModel}</info><br>											
											</address> 
								    	</div>
	  								</div>
								</div>
							</c:if>
		                </div><!--/informationTab-->		                
		            </div><!--/row-->
		            
		            <div class="hero">
                		<legend><img src="/mydbaasmonitor/img/charts.png"> Dashboard</legend>
                		
                		<c:if test="${host.status == true}">
	                	    <div class="row" style="padding-left:30px; margin-bottom:30px;">
					        	<div class="span5">
					          		<h5>Active Domains</h5>
					         		<div id="container1" class="dynamic_chart"></div>
					        	</div>
					        	<div class="span5" style="margin-left:80px;">
					          		<h5>Inactive Domains</h5>
					          		<div id="container2" class="dynamic_chart"></div>
					      		</div>
					      	</div>				      
					        <div class="row" style="padding-left:30px; margin-bottom:30px;">
					        	<div class="span5">
					          		<h5>CPU Utilization</h5>
					         		<div id="container3" class="dynamic_chart"></div>
					        	</div>
					        	<div class="span5" style="margin-left:80px;">
					          		<h5>Memory Utilization</h5>
					          		<div id="container4" class="dynamic_chart"></div>
					        	</div>
					      	</div>				      
					        <div class="row" style="padding-left:30px; margin-bottom:30px;">				        	
					        	<div class="span5">
					          		<h5>Network I/O (Bytes)</h5>
					          		<div id="container5" class="dynamic_chart"></div>
					       		</div>
					       		<div class="span5" style="margin-left:80px;">
					          		<h5>Network I/O (Packets)</h5>
					          		<div id="container6" class="dynamic_chart"></div>
					        	</div>
					      	</div>				      
					       	<div class="row" style="padding-left:30px; margin-bottom:30px;">
					        	<div class="span5">
					          		<h5>Disk I/O Utilization (Requests)</h5>
					          		<div id="container7" class="dynamic_chart"></div>
					        	</div>
					        	<div class="span5" style="margin-left:80px;">
					          		<h5>Disk I/O Utilization (Bytes)</h5>
					          		<div id="container8" class="dynamic_chart"></div>
					       		</div>
					      	</div>				      
					      	<div class="row" style="padding-left:30px; margin-bottom:30px;">
					      		<div class="span5">
					          		<h5>Disk Percentage</h5>
					          		<div id="container9" class="dynamic_chart"></div>
					       		</div>
					        	<div class="span5" style="margin-left:80px;">
					          		<h5>Disk Status</h5>
					          		<div id="container10" class="dynamic_chart"></div>
					        	</div>
					      	</div>
				      	</c:if>
				      	<c:if test="${host.status == false}">
				      		<div class="alert" style="margin-top:5px;">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								There is no <strong>active</strong> monitoring agent for this host.
							</div>
				      	</c:if>                      		
            		</div><!--/dashboard--> 
            		            		       
        		</div><!--/span-->       		
    		</div><!--/row-->
		</div><!--/.fluid-container-->
		
		<!-- Modal -->
		<div id="modalViewDetails" class="modal centered hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  			<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel">Modal header</h3>
  			</div>
  			<div class="modal-body centered_dynamic_chart" id="modal_body">
  			</div>
  			<div class="modal-footer">
    			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  			</div>
		</div>
		
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
	  				<input class="input-xlarge" type="text" readonly="readonly" value="${host.alias}" />
	  				
	  				<div id="div_identifier_host">
		  				<label class="text-info" for="alias">Identifier in Host:</label>
						<input class="input-xlarge" name="virtualMachine.identifierHost" id="identifier_host" type="text" placeholder="Identifier in the hypervisor" />
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
			
				<!-- 
					<label class="text-info" for="key">Key:</label>
					<div class="input-append">
 							<input class="input-medium" name="virtualMachine.key" id="key" type="text" readonly="readonly" />
 							<button class="btn btn-primary" type="button">Send</button>
					</div>
					<span class="help-block"><em><small>If you need an access <strong>key</strong>, upload the file instead of entering the password.</small></em></span>
				 -->
				 
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
		
		<%@include file="/static/javascript_footer.jsp"%>		
		<script src="http://code.highcharts.com/stock/highstock.js" type="text/javascript"></script>
	    <script src="http://code.highcharts.com/highcharts.js" type="text/javascript"></script>
	    <script src="http://code.highcharts.com/modules/exporting.js" type="text/javascript"></script>
	    <script src="${pageContext.servletContext.contextPath}/js/host_view.js" type="text/javascript"></script>
	    <%@include file="/static/footer.jsp"%>	
	</body>
</html>
