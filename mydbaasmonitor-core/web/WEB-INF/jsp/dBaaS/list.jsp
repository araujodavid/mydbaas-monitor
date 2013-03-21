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

		<title>MyDBaaSMonitor - List of Database-as-a-Service</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3">
        		
        			<legend>
						<div align="left" style="margin-bottom:10px;">
							<a class="btn btn-inverse" href="#myNewDBaaS" data-toggle="modal" title="To create a new DBaaS."><i class="icon-plus icon-white"></i> Database-as-a-Service</a>
	        			</div>
        			</legend>
        			
        			<i class="icon-list" style="margin-right:5px; margin-bottom:10px;"></i><strong>Environments:</strong>   
        			 			
		            <div class="accordion" id="accordion2">
		            	<c:forEach items="${dBaaSList}" var="dbaas">
		            		<c:if test="${!dbaas.machines.isEmpty()}">
	  							<div class="accordion-group">
	    							<div class="accordion-heading">
	      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapse${dbaas.id}">
	        								<i class="icon-globe" style="margin-right:6px;"></i>${dbaas.alias}
	     	 							</a>	     	 							
	    							</div>
	    							<div id="collapse${dbaas.id}" class="accordion-body collapse">
	    								<c:forEach items="${dbaas.machines}" var="machine">
		   									<div class="accordion-inner">
		   										 <a class="muted" style="margin-left:15px;" href="<c:url value="/vms/view/${machine.id}"/>"><i class="icon-tag" style="margin-right:4px;"></i>${machine.alias}</a>    									
		     								</div>
	     								</c:forEach>  								
	    							</div>
	  							</div>
	  						</c:if>
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
	  				
	  				<legend>
        				<img src="/mydbaasmonitor/img/fire.png"> <strong>Hot Environments</strong>
        			</legend>
	  				        		
		            <div class="row-fluid">
		            	
		            	<c:forEach items="${highlightsDBaaS}" var="highlight">
		            		<c:if test="${!highlight.machines.isEmpty()}">
				                <div class="span4">
				                    <h3 class="text-info">${highlight.alias}</h3>
				                    <p>
				                    	<strong>Amount of machines:</strong> ${highlight.machines.size()}
				                    </p>
				                    <p><a class="btn" href="<c:url value="/dbaas/view/${highlight.id}"/>">About &raquo;</a></p>
				                </div><!--/span-->
				        	</c:if>
		                </c:forEach>
		            </div><!--/row-->		            
		            
		            <div class="hero" style="margin-top:50px;">
		            	<legend>
        					<img src="/mydbaasmonitor/img/table.png"> <strong>List of Database-as-a-Service</strong>
        				</legend>
		            	<table class="table table-striped">
  							<thead>
							    <tr>
							    	<th><i class="icon-tags" style="margin-left:5px;" title="Unique identifier."></i></th>
							      	<th>Alias</th>
							      	<th><i class="icon-calendar"></i> Record Date</th>
							      	<th>Description</th>
							      	<th><i class="icon-wrench"></i> Informations</th>							      	
							    </tr>
  							</thead>
  							<tbody>
  								<c:forEach items="${allDBaaS}" var="dbaas">
    								<tr>    								
								      	<td>
								      		<span class="badge badge-inverse">${dbaas.id}</span>
								      	</td>
								      	<td>${dbaas.alias}</td>
								      	<td>${dbaas.recordDate}</td>
								      	<td>${dbaas.description}</td>								      	
								      	<td>
											<a href="<c:url value="/dbaas/view/${dbaas.id}"/>" title="Click here to view more detail of the DBaaS.">About</a>
								      	</td>						
    								</tr>
    							</c:forEach>
  							</tbody>
						</table>
		            </div>        
        		</div><!--/span-->
    		</div><!--/row-->
    		
    		<%@include file="/static/footer.jsp"%> 		

		</div><!--/.fluid-container-->
		
		<!-- New DBaaS -->
 		<form method="post" action="${pageContext.servletContext.contextPath}/dbaas/add" id="myNewDBaaS" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 		<div class="modal-header">
    			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    			<h3 id="myModalLabel">New Database-as-a-Service</h3>
  			</div>
  			<div class="modal-body">	  				
				<fieldset>	  				
	  				<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="entity.alias" id="alias" type="text" value="${entity.alias}" placeholder="To better identify the DBaaS environment." />
					<span class="help-block"><em><small>Example: DBaaS Project Alpha</small></em></span>
					
					<label class="text-info" for="description">Description:</label>	  							
 					<textarea name="entity.description" id="description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;" >${entity.description}</textarea>
						
					<label class="text-info" for="recordDate">Record Date:</label>
					<input class="input-small" name="entity.recordDate" id="recordDate" type="text" readonly="readonly" value="${current_date}" />			  	
  				</fieldset>		 		
  			</div>
  			<div class="modal-footer">
  				<button type="submit" class="btn btn-success">Save</button>
  				<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Cancel</button>    			
  			</div>
 		</form> 	
	  	
	  	<!-- JS -->
	  	<script src="http://code.jquery.com/jquery-latest.js"></script>
    	<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
	</body>
</html>