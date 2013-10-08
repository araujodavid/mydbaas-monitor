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
      			width: 500px;
      		}
      		.dynamic_chart2{
      			height: 250px; 
      			width: 100%;
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

		<title>MyDBaaSMonitor - Virtual Machine: ${virtualMachine.alias}</title>
	</head>
	<body>
		<%@include file="/static/menu.jsp"%>

		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3">
        			<legend>
						<div align="left" style="margin-bottom:10px;">
							<a class="btn btn-inverse" href="#myModalNewDBMS" data-toggle="modal" title="To create a new DBMS."><i class="icon-plus icon-white"></i> Database Management System</a>
	        			</div>
        			</legend>

        			<i class="icon-list" style="margin-right:5px; margin-bottom:10px;"></i><strong>List of Database Management Systems:</strong> 
						<c:if test="${virtualMachine.dbmsList.isEmpty()}">
							<div class="alert" style="margin-top:5px;">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								There are no <strong>database management systems</strong>.
							</div>
						</c:if>
			            <div class="accordion" id="accordion2">
			            	<c:forEach items="${virtualMachine.dbmsList}" var="dbms">
	  							<div class="accordion-group">
	    							<div class="accordion-heading">
	      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapse${dbms.id}">
	        								<i class="icon-folder-close" style="margin-right:6px;"></i>${dbms.alias}
	     	 							</a>
	    							</div>
	    							<div id="collapse${dbms.id}" class="accordion-body collapse">
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
        		
        			<legend><img src="/mydbaasmonitor/img/display.png"> Virtual Machine Information</legend>
        			<div class="row-fluid">
		                <div class="span4">
		                    <address>
		                    	<input id="resource_id_chart" type="hidden" value="${virtualMachine.id}" />		                    	
		                    		                    	
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
		  						<c:if test="${virtualMachine.host.id > 0}">
		  							<strong>Host:</strong> <a href="<c:url value="/hosts/view/${virtualMachine.host.id}"/>" title="Link to view the host.">${virtualMachine.host.alias}</a><br>
		  						</c:if>
		  						<strong>Alias:</strong> <info class="muted">${virtualMachine.alias}</info><br>
							  	<strong>Address:</strong> <info class="muted">${virtualMachine.address}</info><br>							  	
							  	<strong>SSH Port:</strong> <info class="muted">${virtualMachine.port}</info><br>
							  	<strong>Username:</strong> <info class="muted">${virtualMachine.user}</info><br>
							  	<c:if test="${virtualMachine.identifierHost != null && !virtualMachine.identifierHost.isEmpty()}">
							  		<input id="resource_id_host" type="hidden" value="${virtualMachine.identifierHost}" />
							 		<strong>Identificer in Host:</strong> <info class="muted">${virtualMachine.identifierHost}</info><br>
							 	</c:if>
							  	<strong>Record Date:</strong> <info class="muted">${virtualMachine.recordDate}</info><br>
							  	<strong>Description:</strong> <info class="muted">${virtualMachine.description}</info><br><br>
							  	<a class="btn btn-warning" href="<c:url value="/vms/edit/${virtualMachine.id}"/>" title="This button updates the information to access the machine."><i class="icon-pencil"></i> Edit</a>
							  	<a class="btn btn-success" href="<c:url value="/agent/machine/${virtualMachine.id}"/>" title="This button updates the information about the monitoring agent."><i class="icon-repeat"></i> Start Monitor</a>
							  	<c:if test="${virtualMachine.status == true}">
							  		<a class="btn btn-danger" href="<c:url value="/agent/stop/machine/${virtualMachine.id}"/>" title="This button stops the monitoring agent." onclick="return confirm('Are you sure you want to stop the monitoring?');"><i class="icon-remove"></i> Stop Monitor</a>
								</c:if>
							</address> 
		                </div><!--/informationAccess-->
                
		                <div class="span4">
		                	<c:if test="${virtualMachine.information.machineOperatingSystem != null && virtualMachine.information.machineTotalMemory > 0}">
			                	<div class="tabbable">
									<ul class="nav nav-tabs">
										<li class="active"><a href="#tab1" data-toggle="tab">Geral</a></li>
								    	<li><a href="#tab2" data-toggle="tab">CPU</a></li>
								  	</ul>
								  	<div class="tab-content">
								    	<div class="tab-pane active" id="tab1">
								      		<address>
						  						<strong>Operating System:</strong> <info class="muted">${virtualMachine.information.machineOperatingSystem}</info><br>
						  						<strong>Architecture:</strong> <info class="muted">${virtualMachine.information.machineArchitecture}</info><br>
						  						<strong>Kernel:</strong> <info class="muted">${virtualMachine.information.machineKernelName}</info><br>
						  						<strong>Version:</strong> <info class="muted">${virtualMachine.information.machineKernelVersion}</info><br>
						  						<strong>Memory:</strong> <info class="muted"><c:if test="${virtualMachine.information.machineTotalMemory > 0}">${virtualMachine.information.machineTotalMemory} GB</c:if></info><br>
						  						<c:if test="${virtualMachine.information.machineTotalSwap > 0}">
						  							<strong>Swap:</strong> <info class="muted">${virtualMachine.information.machineTotalSwap} GB</info><br>
						  						</c:if>										
											</address> 
								    	</div>
	    								<div class="tab-pane" id="tab2">
	      									<address>
						  						<strong>Total CPUs:</strong> <info class="muted"><c:if test="${virtualMachine.information.machineTotalCPUCores > 0}">${virtualMachine.information.machineTotalCPUCores}</c:if></info><br>
						  						<strong>Physical CPUs:</strong> <info class="muted"><c:if test="${virtualMachine.information.machineTotalCPUSockets > 0}">${virtualMachine.information.machineTotalCPUSockets}</c:if></info><br>
						  						<strong>Cores per CPU:</strong> <info class="muted"><c:if test="${virtualMachine.information.machineTotalCoresPerSocket > 0}">${virtualMachine.information.machineTotalCoresPerSocket}</c:if></info><br>									
											</address> 
	    								</div>
	  								</div>
								</div>
							</c:if>
		                </div><!--/informationTab-->		                
		            </div><!--/row-->
		            
		            <div class="hero">
                		<legend><img src="/mydbaasmonitor/img/charts.png"> Dashboard</legend>
                		
                		<c:if test="${virtualMachine.status == true}">                			               		
	                	    <div class="row" style="padding-left:30px; margin-bottom:30px;">
					        	<div class="span5">
					          		<h5>CPU Utilization</h5>
					         		<div id="container1" class="dynamic_chart"></div>
					        	</div>
					        	<div class="span5" style="margin-left:80px;">
					          		<h5>Memory Utilization</h5>
					          		<div id="container2" class="dynamic_chart"></div>
					        	</div>
					      	</div>
					      	<c:if test="${virtualMachine.identifierHost != null && !virtualMachine.identifierHost.isEmpty()}">
                				<div class="row" style="padding-left:30px; margin-bottom:30px;">
						        	<div class="span5">
						          		<h5>Host CPU Utilization</h5>
						         		<div id="container9" class="dynamic_chart"></div>
						        	</div>
						        	<div class="span5" style="margin-left:80px;">
						          		<h5>Host Memory Utilization</h5>
						          		<div id="container10" class="dynamic_chart"></div>
						        	</div>
					      		</div>
                			</c:if> 				      
					        <div class="row" style="padding-left:30px; margin-bottom:30px;">				        	
					        	<div class="span5">
					          		<h5>Network I/O (Bytes)</h5>
					          		<div id="container3" class="dynamic_chart"></div>
					       		</div>
					       		<div class="span5" style="margin-left:80px;">
					          		<h5>Network I/O (Packets)</h5>
					          		<div id="container4" class="dynamic_chart"></div>
					        	</div>
					      	</div>
					      	
					      	<div class="row" style="padding-left:30px; margin-bottom:30px;">				        	
					        	<div class="span5">
					          		<h5>Disk I/O Utilization (Requests)</h5>
					          		<div id="container5" class="dynamic_chart"></div>
					       		</div>
					       		<div class="span5" style="margin-left:80px;">
					          		<h5>Disk I/O Utilization (Bytes)</h5>
					          		<div id="container6" class="dynamic_chart"></div>
					        	</div>
					      	</div>
		      				      
					      	<div class="row" style="padding-left:30px; margin-bottom:30px;">
					      		<div class="span5">
					          		<h5>Disk Percentage</h5>
					          		<div id="container7" class="dynamic_chart"></div>
					       		</div>
					        	<div class="span5" style="margin-left:80px;">
					          		<h5>Disk Status</h5>
					          		<div id="container8" class="dynamic_chart"></div>
					        	</div>
					      	</div>
				      	</c:if>
				      	<c:if test="${virtualMachine.status == false}">
				      		<div class="alert" style="margin-top:5px;">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								There is no <strong>active</strong> monitoring agent for this virtual machine.
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
		
		<!-- Modal New DBMS -->
 		<form method="post" action="<c:url value="/dbmss/add"/>" id="myModalNewDBMS" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel"><img src="/mydbaasmonitor/img/new_database.png"> New DBMS</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>			
	  				<input name="entity.machine.id" id="id" type="hidden" value="${virtualMachine.id}" />
	  				<input name="entity.address" id="address" type="hidden" value="${virtualMachine.address}" />
	  				
	  				<label class="text-info" for="type">DBMS Type:</label>
	  				<select id="type" name="entity.type" style="width:284px;">
	  					<option value="select" selected="selected">Select one</option>
						<option value="MySQL">MySQL</option>
						<option value="PostgreSQL">PostgreSQL</option>
	  				</select>
	  				
	  				<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="entity.alias" id="alias" type="text" placeholder="To better identify the resource" required />
					<span class="help-block"><em><small>Example: DBMS Project X</small></em></span>
					
					<label class="text-info" for="user">User:</label>
					<input name="entity.user" id="user" type="text" placeholder="Database username" required />
					
					<label class="text-info" for="port">Port:</label>
					<input class="input-small" name="entity.port" id="port" type="text" placeholder="e.g. 3604" required />							
					<span class="help-block"><em><small>Enter the DBMS port.</small></em></span>
					
					<label class="text-info" for="password">Password:</label>
					<input name="entity.password" id="password" type="password" required /> <br>
					<input name="confirmPassword" id="confirmPassword" type="password" placeholder="Confirm the password" required />
					
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
 		
		<%@include file="/static/javascript_footer.jsp"%>	
		<script src="http://code.highcharts.com/stock/highstock.js" type="text/javascript"></script>
	    <script src="http://code.highcharts.com/highcharts.js" type="text/javascript"></script>
	    <script src="http://code.highcharts.com/modules/exporting.js" type="text/javascript"></script>
	    <script src="${pageContext.servletContext.contextPath}/js/vms_view.js" type="text/javascript"></script>
	    <%@include file="/static/footer.jsp"%>
	</body>
</html>
