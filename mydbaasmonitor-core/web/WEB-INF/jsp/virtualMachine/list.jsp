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
        		<div class="span3">
        			<div align="left">
        				<a class="btn btn-inverse" href="${pageContext.servletContext.contextPath}/vms/new" title=""><i class="icon-plus icon-white"></i> Machine</a>
        			</div>          			
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
							      	<th>Host</th>
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
								      	<td>${virtualMachine.alias}</td>
								      	<td>${virtualMachine.host}</td>
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
	  	
	  	<script src="http://code.jquery.com/jquery-latest.js"></script>
    	<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>	  	
	</body>
</html>