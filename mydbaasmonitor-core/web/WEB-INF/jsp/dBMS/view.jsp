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

		<title>MyDBaaSMonitor - DBMS: ${dbms.alias}</title>
	</head>
	<body>
		<%@include file="/static/menu.jsp"%>

		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3">
        			<legend>
						<div align="left" style="margin-bottom:10px;">
							<a class="btn btn-inverse" href="#myModalNewDatabase" data-toggle="modal" title="To register a new Machine."><i class="icon-plus icon-white"></i> Database</a>
	        			</div>
        			</legend>

        			<i class="icon-list" style="margin-right:5px; margin-bottom:10px;"></i><strong>List of Databases:</strong> 
						<c:if test="${dbms.databases.isEmpty()}">
							<div class="alert" style="margin-top:5px;">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								There are no <strong>databases</strong>.
							</div>
						</c:if>
			            <div class="accordion" id="accordion2">
			            	<c:forEach items="${dbms.databases}" var="database">
	  							<div class="accordion-group">
	    							<div class="accordion-heading">
	      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapse${database.id}">
	        								<i class="icon-hdd" style="margin-right:6px;"></i>${database.alias}
	     	 							</a>
	    							</div>
	    							<div id="collapse${database.id}" class="accordion-body collapse">
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
        		</div><!--/span3-->

        		<div class="span9">
        			<c:if test="${notice != null}">							
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${notice}
						</div>		  				
	  				</c:if>
        		
        			<legend><img src="/mydbaasmonitor/img/dbms.png"> Database Management System Information</legend>
        			<div class="row-fluid">
		                <div class="span4">
		                    <address>		                    	
		                    	<strong>Monitoring Status:</strong><br> 
		                    	<c:choose>
     								<c:when test="${dbms.status == true}">
      									<span class="label label-success">Active</span>
		        					</c:when>
		        					<c:otherwise>
      									<span class="label label-important">Inactive</span>
      								</c:otherwise>
     							</c:choose><br><br>	                    
		                    	
		                    	<strong>Virtual Machine:</strong> <a href="<c:url value="/vms/view/${dbms.machine.id}"/>" title="Link to view the virtual machine.">${dbms.machine.alias}</a><br>
		  						<strong>Alias:</strong> <info class="muted">${dbms.alias}</info><br>							  	
							  	<strong>Port:</strong> <info class="muted">${dbms.port}</info><br>
							  	<strong>Username:</strong> <info class="muted">${dbms.user}</info><br>
							  	<strong>Record Date:</strong> <info class="muted">${dbms.recordDate}</info><br>
							  	<strong>Description:</strong> <info class="muted">${dbms.description}</info><br><br>
							  	<a class="btn btn-success" href="<c:url value="/dbmss/edit/${dbms.id}"/>" title="This button updates the information about the DBMS."><i class="icon-pencil"></i> Edit</a>
							  	<a class="btn btn-warning" href="<c:url value="/dbmss/list"/>" title="This button updates the information about the resources of the host." onclick="return confirm('Are you sure want to update the information about the resources?');"><i class="icon-wrench"></i> Update</a>
							  	<a class="btn btn-danger" href="<c:url value="/dbmss/list"/>" title="This button deletes the registry of the DBMS." onclick="return confirm('Are you sure want to delete the record?');"><i class="icon-remove"></i> Delete</a>
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
					  						<strong>TODO!</strong>										
										</address> 
							    	</div>
  								</div>
							</div>
		                </div><!--/informationTab-->		                
		            </div><!--/row-->
		            
		            <div class="hero">
                		<legend><img src="/mydbaasmonitor/img/charts.png"> Dashboard</legend>
                		
                	    <div class="row" style="padding-left:30px; margin-bottom:30px;">
				        	<div class="span5">
				          		<h5>CPU Utilization</h5>
				         		<div id="container1" class="dynamic_chart"></div>
				          		<p><a class="btn" href="#modalViewDetails" id="cpu_time" onclick="setModalValues(this.id)"  data-toggle="modal" title="To create a new DBMS.">View details &raquo;</a></p>
				        	</div>
				        	<div class="span5" style="margin-left:80px;">
				          		<h5>Memory Utilization</h5>
				          		<div id="container2" class="dynamic_chart"></div>
				          		<p><a class="btn" href="#modalViewDetails" id="cpu_time" onclick="setModalValues(this.id)" data-toggle="modal" title="To create a new DBMS.">View details &raquo;</a></p>
				      		</div>
				      	</div>				      
				        <div class="row" style="padding-left:30px; margin-bottom:30px;">
				        	<div class="span5">
				          		<h5>Active Connection</h5>
				          		<div id="container3" class="dynamic_chart"></div>
				          		<p><a class="btn" href="#modalViewDetails" id="cpu_time" onclick="setModalValues(this.id)" data-toggle="modal" title="To create a new DBMS.">View details &raquo;</a></p>
				        	</div>
				        	<div class="span5" style="margin-left:80px;">
				          		<h5>Size Usage</h5>
				          		<div id="container4" class="dynamic_chart"></div>
				          		<p><a class="btn" href="#modalViewDetails" id="cpu_time" onclick="setModalValues(this.id)" data-toggle="modal" title="To create a new DBMS.">View details &raquo;</a></p>
				       		</div>
				      	</div>				      	                 		
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
		
		<!-- Modal New Database -->
 		<form method="post" action="<c:url value="/databases/add"/>" id="myModalNewDatabase" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel"><img src="/mydbaasmonitor/img/table.png"> New Database</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>			
	  				<input name="entity.dbms.id" id="host" type="hidden" value="${dbms.id}" />
	  				
	  				<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="entity.alias" id="alias" type="text" value="${entity.alias}" placeholder="To better identify the resource" required />
					<span class="help-block"><em><small>Example: Database DBMS X</small></em></span>
					
					<label class="text-info" for="name">Name:</label>
					<input name="entity.name" id="name" type="text" value="${entity.name}" placeholder="Schema identifier" required />
					
					<label class="text-info" for="description">Description:</label>	  							
 					<textarea name="entity.description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;">${entity.description}</textarea>
					
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
	    <script src="${pageContext.servletContext.contextPath}/js/host_view.js" type="text/javascript"></script>
	    <%@include file="/static/footer.jsp"%>	
	</body>
</html>
