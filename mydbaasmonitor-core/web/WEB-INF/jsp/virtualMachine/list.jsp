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
        		
        		<!-- Example row of columns -->
		      <div class="row-fluid">
		        <div class="span6">
		          <h2>CPU (all vms)</h2>
		          <div id="cpu_all" style="height: 500px;"></div>
		        </div>
		        <div class="span6">
		          <h2>Memory (all vms)</h2>
		          <div id="memory_all" style="height: 500px;"></div>
		       </div>
		      </div>
		      
        		
        		
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
		                name: 'Value',
		                data: [
		                    ['Active',   ${activePercent}],
		                    {
		                        name: 'Not Active',
		                        y: ${notActivePercent},
		                        sliced: true,
		                        selected: true
		                    },
		                    ['With Host',  ${withHostPercent}],
		                    ['Without Host',${withoutHostPercent}]
		                ]
		            }]
		        });
		    	
		    	
		    	
		    	
	    	var seriesOptions = [],
			yAxisOptions = [],
			seriesCounter = 0,
			names = ['MSFT', 'AAPL', 'GOOG'],
			colors = Highcharts.getOptions().colors;

			$.each(names, function(i, name) {

				$.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename='+ name.toLowerCase() +'-c.json&callback=?',	function(data) {

					seriesOptions[i] = {
						name: name,
						data: data
					};

					// As we're loading the data asynchronously, we don't know what order it will arrive. So
					// we keep a counter and create the chart when all the data is loaded.
					seriesCounter++;

					if (seriesCounter == names.length) {
						createChart();
						createChart2();
					}
				});
			});



			// create the chart when all data is loaded
			function createChart() {

				$('#memory_all').highcharts('StockChart', {
				    chart: {
				    },

				    rangeSelector: {
				        selected: 4
				    },

				    yAxis: {
				    	labels: {
				    		formatter: function() {
				    			return (this.value > 0 ? '+' : '') + this.value + '%';
				    		}
				    	},
				    	plotLines: [{
				    		value: 0,
				    		width: 2,
				    		color: 'silver'
				    	}]
				    },
				    
				    plotOptions: {
				    	series: {
				    		compare: 'percent'
				    	}
				    },
				    
				    tooltip: {
				    	pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
				    	valueDecimals: 2
				    },
				    
				    series: seriesOptions
				});
			}
			
			
			
			// create the chart when all data is loaded
			function createChart2() {

				$('#cpu_all').highcharts('StockChart', {
				    chart: {
				    },

				    rangeSelector: {
				        selected: 4
				    },

				    yAxis: {
				    	labels: {
				    		formatter: function() {
				    			return (this.value > 0 ? '+' : '') + this.value + '%';
				    		}
				    	},
				    	plotLines: [{
				    		value: 0,
				    		width: 2,
				    		color: 'silver'
				    	}]
				    },
				    
				    plotOptions: {
				    	series: {
				    		compare: 'percent'
				    	}
				    },
				    
				    tooltip: {
				    	pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
				    	valueDecimals: 2
				    },
				    
				    series: seriesOptions
				});
			}
		    	
		    	
	    	
		    	
		    	
		    	
		    	
		    });
		    
		});
		</script>
		
		<script src="http://code.highcharts.com/stock/highstock.js"></script>
<script src="http://code.highcharts.com/stock/modules/exporting.js"></script>
	</body>
</html>