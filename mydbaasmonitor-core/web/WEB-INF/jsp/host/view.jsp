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
		
		<!-- Modal New DBMS -->
 		<form method="post" action="<c:url value="/machine/add"/>" id="myModalNewDBMS" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel"><img src="/mydbaasmonitor/img/new_database.png"> New Virtual Machine</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>			
	  						  	
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
