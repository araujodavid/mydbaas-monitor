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

		<title>MyDBaaSMonitor - List of DBMSs</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3" style="margin-top:10px;">
        			<div align="left">
        				<a class="btn btn-inverse" href="${pageContext.servletContext.contextPath}/databases/new" title=""><i class="icon-plus icon-white"></i> Database</a>
        			</div>
        			<c:choose>
        				<c:when test="${dbmsList.isEmpty()}">
        					<div class="alert" style="margin-top:30px;">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								DBMSs status graphs <strong>offline</strong>.
							</div>
        				</c:when>
        				<c:otherwise>     
		        			<div id="container-summary" style="margin-top:20px;"></div>
		        			<table id="datatable" class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th></th>
										<th>Active</th>
										<th>Not Active</th>
										<th>w/ DB</th>
										<th>w/o DB</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th>DBMS</th>
										<th>${amountActive}</th>
										<th>${amountNotActive}</th>
										<th>${amountWithDB}</th>
										<th>${amountWithoutDB}</th>
									</tr>
								</tbody>
							</table>
						</c:otherwise>
        			</c:choose>      		     			
        		</div><!--/span-->
        		
        		<div class="span9">
        		
        			<c:if test="${notice != null}">							
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${notice}
						</div>		  				
	  				</c:if>
        		
        			<legend>
        				<img src="/mydbaasmonitor/img/table.png"> <strong>List of Database Management Systems</strong>
        			</legend>
        			
        			<div class="row-fluid">
		            	<table class="table table-striped">
							<caption>
								
							</caption>
  							<thead>
							    <tr>
							    	<th><i class="icon-tags" style="margin-left:5px;" title="Unique identifier."></i></th>
							      	<th>Alias</th>
							      	<th>Type</th>
							      	<th><i class="icon-refresh"></i> Monitoring Status</th>
							      	<th><i class="icon-calendar"></i> Record Date</th>
							      	<th><i class="icon-wrench"></i> Informations</th>
							    </tr>
  							</thead>
  							<tbody>
  								<c:forEach items="${dbmsList}" var="dbms">
    								<tr>    								
								      	<td>
								      		<span class="badge badge-inverse">${dbms.id}</span>
								      	</td>
								      	<td>
								      		<a href="${pageContext.servletContext.contextPath}/dbmss/view/${dbms.id}" title="Click here to view more detail of the resource.">${dbms.alias}</a>
								      	</td>
								      	<td>${dbms.type}</td>
								      	<td>
								      		<c:choose>
        										<c:when test="${dbms.status == true}">
        											<i class="status-monitor-active"></i>
				        						</c:when>
				        						<c:otherwise>
        											<i class="status-monitor-inactive"></i>
        										</c:otherwise>
       										</c:choose>								      	
								      	</td>
								      	<td>${dbms.recordDate}</td>
								      	<td>
											<a href="${pageContext.servletContext.contextPath}/dbmss/view/${dbms.id}" title="Click here to view more detail of the resource.">About</a>
								      	</td>						
    								</tr>
    							</c:forEach>
  							</tbody>
						</table>
						<c:if test="${dbmsList.isEmpty()}">
							<div class="alert">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								There are no <strong>Database Management Systems</strong>.
							</div>
						</c:if>               
		            </div><!--/row-->            		            		       
        		</div><!--/span-->       		
    		</div><!--/row-->
    		
    		<%@include file="/static/footer.jsp"%>

		</div><!--/.fluid-container-->	 	
		<%@include file="/static/javascript_footer.jsp"%>
		<script type="text/javascript">
		    $(document).ready(function () {
		    	
		    	$('#container-summary').highcharts({
		            data: {
		                table: document.getElementById('datatable')
		            },
		            chart: {
		                type: 'column'
		            },
		            title: {
		                text: 'DBMS Status'
		            },
		            credits: {
	                    enabled: false
	                },
		            yAxis: {
		                allowDecimals: false,
		                title: {
		                    text: 'Units'
		                }
		            },
		            tooltip: {
		                formatter: function() {
		                    return '<b>'+ this.series.name +'</b><br/>'+
		                        this.y +' '+ this.x.toLowerCase();
		                }
		            }
		        });
		    });
		</script>	      
		<script src="http://code.highcharts.com/highcharts.js" type="text/javascript"></script>
    	<script src="http://code.highcharts.com/modules/data.js"></script>
		<script src="http://code.highcharts.com/modules/exporting.js" type="text/javascript"></script>	
	</body>
</html>