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
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>
    	<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>

		<title>MyDBaaSMonitor - Machine: ${virtualMachine.alias}</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3">
			            <div class="accordion" id="accordion2">
  							<div class="accordion-group">
    							<div class="accordion-heading">
      								<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
        								<i class="icon-tasks" style="margin-right:5px;"></i>Virtual Machines
     	 							</a>
    							</div>
    							<div id="collapseOne" class="accordion-body collapse in">
    								<c:forEach items="${listResources}" var="resource">
    									<div class="accordion-inner">
    										<a href="${pageContext.servletContext.contextPath}/vms/view/${ resource.id }"><i class="icon-tags"></i> ${ resource.alias }</a> [${ resource.host }]        									
      									</div>
    								</c:forEach>     								
    							</div>
  							</div>
  							<div class="accordion-group">
    							<div class="accordion-heading">
      								<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
        								<i class="icon-list-alt" style="margin-right:6px;"></i>Databases
      								</a>
    							</div>
    							<div id="collapseTwo" class="accordion-body collapse">
								    <div class="accordion-inner">
								    	TODO!
									</div>
   								</div>
  							</div>
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
		                    
		  						<strong>Alias:</strong> <em class="text-info">${virtualMachine.alias}</em><br>
							  	<strong>Host:</strong> <em class="text-info">${virtualMachine.host}</em><br>							  	
							  	<strong>SSH Port:</strong> <em class="text-info">${virtualMachine.port}</em><br>
							  	<strong>Username:</strong> <em class="text-info">${virtualMachine.user}</em><br>
							  	<strong>Record Date:</strong> <em class="muted">${virtualMachine.recordDate}</em><br>
							  	<strong>Description:</strong> <em class="muted">${virtualMachine.description}</em><br><br>
							  	<a class="btn btn-success" href="${pageContext.servletContext.contextPath}/vms/edit/${virtualMachine.id}" title="This button updates the information to access the machine."><i class="icon-pencil"></i> Edit</a>
							  	<a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/vms/list" title="This button updates the information about the resources of the machine." onclick="return confirm('Are you sure want to update the information about the resources?');"><i class="icon-wrench"></i> Update</a>
							  	<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/vms/list" title="This button deletes the registry of the machine." onclick="return confirm('Are you sure want to delete the record?');"><i class="icon-remove"></i> Delete</a>
							</address> 
		                </div><!--/informationAccess-->
                
		                <div class="span4">
		                	<div class="tabbable"> <!-- Only required for left/right tabs -->
								<ul class="nav nav-tabs">
									<li class="active"><a href="#tab1" data-toggle="tab">Geral</a></li>
							    	<li><a href="#tab2" data-toggle="tab">CPU</a></li>
							    	<li><a href="#tab3" data-toggle="tab">Disk</a></li>
							  	</ul>
							  	<div class="tab-content">
							    	<div class="tab-pane active" id="tab1">
							      		<address>
					  						<strong>Operating System:</strong> <em class="">${virtualMachine.operatingSystem}</em><br>
					  						<strong>Kernel:</strong> <em class="">${virtualMachine.kernel}</em><br>
					  						<strong>Memory:</strong> <em class="">${virtualMachine.memory} GB</em><br>
					  						<strong>Swap:</strong> <em class="">${virtualMachine.swap} GB</em><br>											
										</address> 
							    	</div>
    								<div class="tab-pane" id="tab2">
      									<address>
					  						<strong>Cores:</strong> <em class="">${virtualMachine.cores}</em>											
										</address> 
    								</div>
    								<div class="tab-pane" id="tab3">
      									<p>Howdy, I'm in Section 2.</p>
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
    		
    		<%@include file="/static/footer.jsp"%>

		</div><!--/.fluid-container-->	 	
	  	
	</body>
</html>