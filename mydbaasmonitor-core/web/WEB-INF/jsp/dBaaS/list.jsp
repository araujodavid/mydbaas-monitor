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
		            	<c:if test="${dBaaSList.isEmpty()}">
							<div class="alert" style="margin-top:30px;">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								There are no <strong>environments</strong> with resources.
							</div>
						</c:if>
		            	<c:forEach items="${dBaaSList}" var="dbaas">
		            		<c:choose>
			            		<c:when test="${!dbaas.hosts.isEmpty() || !dbaas.machines.isEmpty()}">
		  							<div class="accordion-group">
		    							<div class="accordion-heading">
		      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapse${dbaas.id}">
		        								<i class="icon-globe" style="margin-right:6px;"></i>${dbaas.alias}
		     	 							</a>	     	 							
		    							</div>
		    							<div id="collapse${dbaas.id}" class="accordion-body collapse">
		    								<c:if test="${!dbaas.hosts.isEmpty()}">
		    									<div class="accordion-inner">
		    										<strong>Host:</strong><br>
			    									<c:forEach items="${dbaas.hosts}" var="host">
				   										<a class="muted" style="margin-left:15px;" href="<c:url value="/hosts/view/${host.id}"/>"><i class="icon-tag" style="margin-right:4px;"></i>${host.alias}</a><br>    									
				     								</c:forEach>
				     							</div>
		     								</c:if>
		     								<c:if test="${!dbaas.machines.isEmpty()}">
		     									<div class="accordion-inner">
		     										<strong>Virtual Machine:</strong><br>
				    								<c:forEach items="${dbaas.machines}" var="machine">
					   									<a class="muted" style="margin-left:15px;" href="<c:url value="/vms/view/${machine.id}"/>"><i class="icon-tag" style="margin-right:4px;"></i>${machine.alias}</a><br>	     								
				     								</c:forEach>
			     								</div>
		     								</c:if>	
		     								<c:if test="${!dbaas.dbmss.isEmpty()}">
		     									<div class="accordion-inner">
		     										<strong>DBMS:</strong><br>
				    								<c:forEach items="${dbaas.dbmss}" var="dbms">
					   									<a class="muted" style="margin-left:15px;" href="<c:url value="/dbmss/view/${dbms.id}"/>"><i class="icon-tag" style="margin-right:4px;"></i>${dbms.alias}</a><br>	     								
				     								</c:forEach>
			     								</div>
		     								</c:if>
		     								<c:if test="${!dbaas.databases.isEmpty()}">
		     									<div class="accordion-inner">
		     										<strong>Database:</strong><br>
				    								<c:forEach items="${dbaas.databases}" var="database">
					   									<a class="muted" style="margin-left:15px;" href="<c:url value="/databases/view/${database.id}"/>"><i class="icon-tag" style="margin-right:4px;"></i>${database.alias}</a><br>	     								
				     								</c:forEach>
			     								</div>
		     								</c:if>						
		    							</div>
		  							</div>
		  						</c:when>
			            		<c:otherwise>
			            			<div class="alert">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										The <strong>${dbaas.alias}</strong> doesn't have resources.
									</div>
			            		</c:otherwise>
			            	</c:choose>
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
		            	<c:if test="${highlightsDBaaS.isEmpty()}">
							<div class="alert">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								There are no <strong>hot</strong> environments.
							</div>
						</c:if>		            	
		            	<c:forEach items="${highlightsDBaaS}" var="highlight">
		            		<div class="span4">
			            		<c:choose>
			            			<c:when test="${!highlight.hosts.isEmpty() || !highlight.machines.isEmpty()}">			            				
					                    <h3 class="text-info">${highlight.alias}</h3>
					                    <div class="dbaas_div" id="${highlight.id}" style="width: 350px; height: 200px; margin: 0 auto"></div>
					                    <p>
					                    	<div class="infos hosts ${highlight.id}">${highlight.hosts.size()}</div>
					                    	<div class="infos machines ${highlight.id}">${highlight.machines.size()}</div>
					                    	<div class="infos dbmss ${highlight.id}">${highlight.dbmss.size()}</div>
					                    	<div class="infos databases ${highlight.id}">${highlight.databases.size()}</div>
					                    </p>
					                    <p><a class="btn" href="<c:url value="/dbaas/view/${highlight.id}"/>">About &raquo;</a></p>					                	
			            			</c:when>
			            			<c:otherwise>
			            				<div class="alert">
											<button type="button" class="close" data-dismiss="alert">&times;</button>
											There is no <strong>hot</strong> environment.
										</div>
			            			</c:otherwise>
			            		</c:choose>
		            		</div><!--/spanHighlight-->
		                </c:forEach>
		            </div><!--/row-->		            
		            
		            <div class="hero" style="margin-top:50px;">
		            	<legend>
        					<img src="/mydbaasmonitor/img/table.png"> <strong>Others Database-as-a-Service</strong>
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
  								<c:forEach items="${restDBaaS}" var="dbaas">
    								<tr>    								
								      	<td>
								      		<span class="badge badge-inverse">${dbaas.id}</span>
								      	</td>
								      	<td>
								      		<a href="<c:url value="/dbaas/view/${dbaas.id}"/>" title="Click here to view more detail of the DBaaS.">${dbaas.alias}</a>
								      	</td>
								      	<td>${dbaas.recordDate}</td>
								      	<td>${dbaas.description}</td>								      	
								      	<td>
											<a href="<c:url value="/dbaas/view/${dbaas.id}"/>" title="Click here to view more detail of the DBaaS.">About</a>
								      	</td>						
    								</tr>
    							</c:forEach>							
  							</tbody>
						</table>
						<c:if test="${restDBaaS == null || restDBaaS.isEmpty()}">
							<div class="alert">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								There are no others <strong>DBaaS</strong>.
							</div>
						</c:if>
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
					<input class="input-xlarge" name="entity.alias" id="alias" type="text" value="${entity.alias}" placeholder="To better identify the DBaaS environment." required />
					<span class="help-block"><em><small>Example: DBaaS Project Alpha</small></em></span>
					
					<label class="text-info" for="description">Description:</label>	  							
 					<textarea name="entity.description" id="description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;" required>${entity.description}</textarea>
						
					<label class="text-info" for="recordDate">Record Date:</label>
					<input class="input-small" name="entity.recordDate" id="recordDate" type="text" readonly="readonly" value="${current_date}" />			  	
  				</fieldset>		 		
  			</div>
  			<div class="modal-footer">
  				<button type="submit" class="btn btn-success">Save</button>
  				<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Cancel</button>    			
  			</div>
 		</form> 	
	  	
	  	<%@include file="/static/javascript_footer.jsp"%>
    	<script src="${pageContext.servletContext.contextPath}/js/highcharts/highcharts.js"></script>
    	<script src="${pageContext.servletContext.contextPath}/js/highcharts/modules/exporting.js"></script>
    	<script type="text/javascript">
    	$(function () {
    		$('.infos').hide();
    		$(".dbaas_div").each(function(){
    			var host = parseInt($(".infos.hosts."+this.id).text());
    			var machine = parseInt($(".infos.machines."+this.id).text());
    			var dbms = parseInt($(".infos.dbmss."+this.id).text());
    			var database = parseInt($(".infos.databases."+this.id).text());
				/*Add news parameters */    			
    			var options = {
    	                chart: {
    	                    type: 'bar'
    	                },
    	                title: {
    	                    text: 'Status'
    	                },
    	                xAxis: {
    	                    categories: ['DBaaS'],
    	                    title: {
    	                        text: null
    	                    }
    	                },
    	                yAxis: {
    	                    min: 0,
    	                    title: {
    	                        text: 'Amount',
    	                        align: 'high'
    	                    },
    	                    labels: {
    	                        overflow: 'justify'
    	                    }
    	                },
    	                tooltip: {
    	                    valueSuffix: ' resource(s)'
    	                },
    	                plotOptions: {
    	                    bar: {
    	                        dataLabels: {
    	                            enabled: false
    	                        }
    	                    }
    	                },
    	                credits: {
    	                    enabled: false
    	                },
    	                series: [{
    	                    name: 'Hosts',
    	                    data: [host]
    	                }, {
    	                    name: 'VMs',
    	                    data: [machine]
    	                },{
    	                    name: 'DBMSs',
    	                    data: [dbms]
    	                } ,{
    	                    name: 'Databases',
    	                    data: [database]
    	                }]
    	            };    			
    			$("#"+this.id).highcharts(options);  
    		});
        });
    	</script>
	</body>
</html>