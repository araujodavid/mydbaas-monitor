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
    	<script src="http://code.jquery.com/jquery-2.0.3.min.js" type="text/javascript"></script>

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
						<input id="resource_id_chart" type="hidden" value="${database.id}" />	
						            	
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
                		
                		<c:if test="${database.status == true}">
                			<div class="row" style="padding-left:30px; margin-bottom:30px;">
					        	<div class="span5">
					          		<h5>Active Connection</h5>
					          		<div id="container1" class="dynamic_chart"></div>
					        	</div>
					        	<div class="span5" style="margin-left:80px;">
					          		<h5>Size Usage</h5>
					          		<div id="container2" class="dynamic_chart"></div>
					       		</div>
					      	</div>
					      	
					      	<div class="row" style="padding-left:30px; margin-bottom:30px;">
					      		<h5>Information Tables</h5>
					      		<img id="load-information-table" style="display:none; margin-bottom:5px;" src="${pageContext.servletContext.contextPath}/img/carregando2.gif">
					        	<div class="span10" style="height:500px; overflow:auto;">					          		
					          		<table id="container15" class="table table-bordered">
										<thead>
											<tr>
										      	<th>Table Name</th>
										      	<th>Amount Rows</th>
										    	<th>Row Average (KB)</th>
										    	<th>Data Length (MB)</th>
										    	<th>Index Length (MB)</th>										    	
										    	<th>Total Size (MB)</th>
										    </tr>
										</thead>
										<tbody id="list-tables">
											
										</tbody>
									</table>
					        	</div>
					      	</div>
					      	
					      	<div class="row" style="padding-left:30px; margin-bottom:30px;">
					      		<h5>Workload Trace</h5>
					      		<img id="load-trace-workload" style="display:none; margin-bottom:5px;" src="${pageContext.servletContext.contextPath}/img/carregando2.gif">
					        	<div class="span10" style="height:500px; overflow:auto;">					          		
					          		<table id="container15" class="table table-striped">
										<thead>
											<tr>
										      	<th style="width: 700px;">Query</th>
										      	<th>Selectivity (rows)</th>
										    	<th>Response Time (s)</th>
										    	<th>Throughput (rows/s)</th>
										    	<th>Record</th>
										    </tr>
										</thead>
										<tbody id="trace-workload">
											
										</tbody>
									</table>
					        	</div>
					      	</div>
				      	</c:if>
				      	<c:if test="${database.status == false}">
				      		<div class="alert" style="margin-top:5px;">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								There is no <strong>active</strong> monitoring for this database.
							</div>
				      	</c:if>						      	                 		
            		</div><!--/dashboard-->         		            		       
        		</div><!--/span9-->            		            		       
        	</div><!--/span-->       		
    	</div><!--/row-->
 		
	 	<%@include file="/static/javascript_footer.jsp"%>		
	    <script src="http://code.highcharts.com/stock/highstock.js" type="text/javascript"></script>
	    <script src="http://code.highcharts.com/highcharts.js" type="text/javascript"></script>
	    <script src="http://code.highcharts.com/modules/exporting.js" type="text/javascript"></script>
	    <script src="${pageContext.servletContext.contextPath}/js/database_view.js" type="text/javascript"></script>
	    <script type="text/javascript">
	    	var old_date = new Date().getTime();
	    
		    setInterval(function() {
                var resource_id = parseInt($("#resource_id_chart").val());
                
                
                $("#load-trace-workload").css("display", "block");
                
                $.post("http://localhost:8080/mydbaasmonitor/metric/single", {metricName : "WorkloadStatus", metricType:"database", resourceType:"database", queryType: 1, resourceID: resource_id },function(data) {
          			
                	var now_date = new Date(data[0].recordDate).getTime();
                		
               		if (now_date > old_date) {
               			$("#trace-workload").append("<tr>" +
					      		"<td><center class='text-info' style='font-size: medium; font-weight:bold;'>"+data[0].workloadStatusQuery+"</center></td>" +
					      		"<td><center class='muted' style='font-size: medium; font-weight:bold;'>"+data[0].workloadStatusSelectivity+"</center></td>" +
					      		"<td><center class='muted' style='font-size: medium; font-weight:bold;'>"+data[0].workloadStatusResponseTime+"</center></td>" +
					      		"<td><center class='muted' style='font-size: medium; font-weight:bold;'>"+data[0].workloadStatusThroughput+"</center></td>" +
					      		"<td><center class='muted' style='font-size: medium; font-weight:bold;'>"+data[0].recordDate+"</center></td>" +
							 "</tr>");
					};
					
					old_date = now_date;
              	});
                
                $("#load-trace-workload").css("display", "none");
                
            }, 2000);
	    </script>
	    <script type="text/javascript">
		    setInterval(function() {
                var resource_id = parseInt($("#resource_id_chart").val());
                
                $("#load-information-table").css("display", "block");
                
                $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "InformationTable", metricType:"database", resourceType:"database", queryType: 1, resourceID: resource_id },function(data) {
                	
                	$("#list-tables").empty();
                	
                	for (var i = 0; i < data.length; i++) {                		
                		$("#list-tables").append("<tr>" +
											      		"<td><center class='muted' style='font-size: medium; font-weight:bold;'>"+data[i].informationTableName+"</center></td>" +
											      		"<td><center class='muted' style='font-size: medium; font-weight:bold;'>"+data[i].informationTableAmountRows+"</center></td>" +
											      		"<td><center class='muted' style='font-size: medium; font-weight:bold;'>"+data[i].informationTableRowAverage+"</center></td>" +
											      		"<td><center class='muted' style='font-size: medium; font-weight:bold;'>"+data[i].informationTableDataLength+"</center></td>" +
											      		"<td><center class='muted' style='font-size: medium; font-weight:bold;'>"+data[i].informationTableIndexLength+"</center></td>" +
											      		"<td><center class='muted' style='font-size: medium; font-weight:bold;'>"+data[i].informationTableTotalSize+"</center></td>" +
													 "</tr>");
                	};
              	});
                
                $("#load-information-table").css("display", "none");
                
            }, 15000);
	    </script>
	    <%@include file="/static/footer.jsp"%>
	</body>
</html>
