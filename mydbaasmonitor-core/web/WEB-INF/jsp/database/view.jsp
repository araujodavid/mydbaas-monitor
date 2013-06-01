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

		<title>MyDBaaSMonitor - Database: ${database.alias}</title>
	</head>
	<body>
		<%@include file="/static/menu.jsp"%>

		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3">        			
        			<legend>
						<img src="/mydbaasmonitor/img/table.png"> Database Information:
        			</legend>
					<address>		                    	
                    	<strong>Monitoring Status:</strong><br> 
                    	<c:choose>
   							<c:when test="${database.status == true}">
    							<span class="label label-success">Active</span>
        					</c:when>
        					<c:otherwise>
    							<span class="label label-important">Inactive</span>
    						</c:otherwise>
   						</c:choose><br><br>	                    
                    	
                    	<strong>DBMS:</strong> <a href="<c:url value="/vms/view/${database.dbms.id}"/>" title="Link to view the virtual machine.">${database.dbms.alias}</a><br>
  						<strong>Alias:</strong> <info class="muted">${database.alias}</info><br>							  	
					  	<strong>Schema Name:</strong> <info class="muted">${database.name}</info><br>
					  	<strong>Record Date:</strong> <info class="muted">${database.recordDate}</info><br>
					  	<strong>Description:</strong> <info class="muted">${database.description}</info><br><br>
					  	<a class="btn btn-success" href="<c:url value="/databases/edit/${database.id}"/>" title="This button updates the information about the database."><i class="icon-pencil"></i> Edit</a>
					  	<a class="btn btn-warning" href="<c:url value="/databases/list"/>" title="This button updates the information about the resources of the database." onclick="return confirm('Are you sure want to update the information about the resources?');"><i class="icon-wrench"></i> Update</a>
					  	<a class="btn btn-danger" href="<c:url value="/databases/list"/>" title="This button deletes the registry of the database." onclick="return confirm('Are you sure want to delete the record?');"><i class="icon-remove"></i> Delete</a>
					</address>
        		</div><!--/span-->

        		<div class="span9">
        			<c:if test="${notice != null}">							
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${notice}
						</div>		  				
	  				</c:if>
		            
		            <div class="hero">
                		<legend><img src="/mydbaasmonitor/img/charts.png"> Dashboard</legend>
                		
                	    <div class="row" style="padding-left:30px; margin-bottom:30px;">
				        	<div class="span5">
				          		<h5>CPU Usage</h5>
				         		<div id="container1" class="dynamic_chart"></div>
				          		<p><a class="btn" href="#modalViewDetails" id="cpu_time" onclick="setModalValues(this.id)"  data-toggle="modal" title="To create a new DBMS.">View details &raquo;</a></p>
				        	</div>
				        	<div class="span5" style="margin-left:80px;">
				          		<h5>Load Percentage</h5>
				          		<div id="container2" class="dynamic_chart"></div>
				          		<p><a class="btn" href="#modalViewDetails" id="cpu_time" onclick="setModalValues(this.id)" data-toggle="modal" title="To create a new DBMS.">View details &raquo;</a></p>
				      		</div>
				      	</div>				      
				        <div class="row" style="padding-left:30px; margin-bottom:30px;">
				        	<div class="span5">
				          		<h5>Physical Memory</h5>
				          		<div id="container3" class="dynamic_chart"></div>
				          		<p><a class="btn" href="#modalViewDetails" id="cpu_time" onclick="setModalValues(this.id)" data-toggle="modal" title="To create a new DBMS.">View details &raquo;</a></p>
				        	</div>
				        	<div class="span5" style="margin-left:80px;">
				          		<h5>Network I/O (Kb/s)</h5>
				          		<div id="container4" class="dynamic_chart"></div>
				          		<p><a class="btn" href="#modalViewDetails" id="cpu_time" onclick="setModalValues(this.id)" data-toggle="modal" title="To create a new DBMS.">View details &raquo;</a></p>
				       		</div>
				      	</div>				      	                 		
            		</div><!--/dashboard-->         		            		       
        		</div><!--/span9-->            		            		       
        	</div><!--/span-->       		
    	</div><!--/row-->
 		
	 	<%@include file="/static/javascript_footer.jsp"%>		
	    <script src="http://code.highcharts.com/stock/highstock.js" type="text/javascript"></script>
	    <script src="http://code.highcharts.com/highcharts.js" type="text/javascript"></script>
	    <script src="http://code.highcharts.com/modules/exporting.js" type="text/javascript"></script>
	    <script src="${pageContext.servletContext.contextPath}/js/host_view.js" type="text/javascript"></script>
	    <%@include file="/static/footer.jsp"%>
	</body>
</html>
