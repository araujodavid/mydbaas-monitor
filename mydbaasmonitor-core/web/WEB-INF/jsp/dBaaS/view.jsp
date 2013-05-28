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
		
		<%@include file="/static/footer.jsp"%>	
	  	<%@include file="/static/javascript_footer.jsp"%>
	  	
	</body>
</html>