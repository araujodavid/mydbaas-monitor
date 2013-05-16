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

		<title>MyDBaaSMonitor - List of Machines</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3" style="margin-top:10px;">
        			<div align="left">
        				<a class="btn btn-inverse" href="${pageContext.servletContext.contextPath}/vms/new" title=""><i class="icon-plus icon-white"></i> Virtual Machine</a>
        			</div>     
        			<div id="container-pizza" style="margin-top:20px;"></div>     			
        		</div><!--/span-->
        		
        		<div class="span9">
        		
        			<c:if test="${notice != null}">							
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${notice}
						</div>		  				
	  				</c:if>
        		
        			<legend>
        				<img src="/mydbaasmonitor/img/table.png"> <strong>List of Virtual Machines</strong>
        			</legend>
        			
        			<div class="row-fluid">
		            	<table class="table table-striped">
							<caption>
								
							</caption>
  							<thead>
							    <tr>
							    	<th><i class="icon-tags" style="margin-left:5px;" title="Unique identifier."></i></th>
							      	<th>Alias</th>
							      	<th>Address</th>
							      	<th><i class="icon-refresh"></i> Monitoring Status</th>
							      	<th><i class="icon-calendar"></i> Record Date</th>
							      	<th><i class="icon-wrench"></i> Informations</th>
							    </tr>
  							</thead>
  							<tbody>
  								<c:forEach items="${virtualMachineList}" var="virtualMachine">
    								<tr>    								
								      	<td>
								      		<span class="badge badge-inverse">${virtualMachine.id}</span>
								      	</td>
								      	<td><a href="${pageContext.servletContext.contextPath}/vms/view/${virtualMachine.id}" title="${virtualMachine.alias}">${virtualMachine.alias}</a></td>
								      	<td>${virtualMachine.address}</td>
								      	<td>
								      		<c:choose>
        										<c:when test="${virtualMachine.status == true}">
        											<i class="status-monitor-active"></i>
				        						</c:when>
				        						<c:otherwise>
        											<i class="status-monitor-inactive"></i>
        										</c:otherwise>
       										</c:choose>								      	
								      	</td>
								      	<td>${virtualMachine.recordDate}</td>
								      	<td>
											<a href="${pageContext.servletContext.contextPath}/vms/view/${virtualMachine.id}" title="Click here to view more detail of the resource.">About</a>
								      	</td>						
    								</tr>
    							</c:forEach>
  							</tbody>
						</table>                
		            </div><!--/row-->            		            		       
        		</div><!--/span-->       		
    		</div><!--/row-->
    		
    		<%@include file="/static/footer.jsp"%>

		</div><!--/.fluid-container-->	 	
	  	
	  	<script src="http://code.jquery.com/jquery-latest.js" type="text/javascript"></script>
    	<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js" type="text/javascript"></script>
    	<script src="http://code.highcharts.com/highcharts.js" type="text/javascript"></script>
		<script src="http://code.highcharts.com/modules/exporting.js" type="text/javascript"></script>
		<script>
		$(function () {
		    var chart;
		    
		    $(document).ready(function () {
		    	
		    	// Build the chart
		        $('#container-pizza').highcharts({
		            chart: {
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false
		            },
		            title: {
		                text: 'Virtual Machines Status'
		            },
		            tooltip: {
		        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
		            	percentageDecimals: 1
		            },
		            plotOptions: {
		                pie: {
		                    allowPointSelect: true,
		                    cursor: 'pointer',
		                    dataLabels: {
		                        enabled: false
		                    },
		                    showInLegend: true
		                }
		            },
		            series: [{
		                type: 'pie',
		                name: 'Browser share',
		                data: [
		                    ['Firefox',   45.0],
		                    ['IE',       26.8],
		                    {
		                        name: 'Chrome',
		                        y: 12.8,
		                        sliced: true,
		                        selected: true
		                    },
		                    ['Safari',    8.5],
		                    ['Opera',     6.2],
		                    ['Others',   0.7]
		                ]
		            }]
		        });
		    });
		    
		});
		</script>	  	
	</body>
</html>