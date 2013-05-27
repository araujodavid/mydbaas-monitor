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

		<title>MyDBaaSMonitor - Machine: ${virtualMachine.alias}</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3">        		
        			<legend>
						<div align="left" style="margin-bottom:10px;">
							<a class="btn btn-inverse" href="#myModalNewDatabase" data-toggle="modal" title="To create a new database."><i class="icon-plus icon-white"></i> Machine</a>
	        			</div>
        			</legend>
        			
        			<i class="icon-list" style="margin-right:5px; margin-bottom:10px;"></i><strong>List of Virtual Machines:</strong> 
        		
			            <div class="accordion" id="accordion2">
							<div class="accordion-group">
    							<div class="accordion-heading">
      								<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapse1">
        								<i class="icon-list-alt" style="margin-right:6px;"></i>Machine 01
     	 							</a>	     	 							
    							</div>
    							<div id="collapse1" class="accordion-body collapse">
   									<div class="accordion-inner">
   										 <address style="margin-bottom:5px;">
   										 	<strong>Username:</strong>
   										 </address>       									
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
        		
        			<legend><img src="/mydbaasmonitor/img/display.png"> Database-as-a-Service Information</legend>
        			<div class="row-fluid">
		                <div class="span4">
		                    <address>		                    	
		                    	<strong>Monitoring Status:</strong><br>		                    	
							</address> 
		                </div><!--/informationAccess-->
                
		                <div class="span4">
		                	<div class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#tab1" data-toggle="tab">Geral</a></li>
							    	<li><a href="#tab2" data-toggle="tab">CPU</a></li>
							    	<li><a href="#tab3" data-toggle="tab">Disk</a></li>
							  	</ul>
							  	<div class="tab-content">
							    	<div class="tab-pane active" id="tab1">
							      		
							    	</div>
    								<div class="tab-pane" id="tab2">
      									 
    								</div>
    								<div class="tab-pane" id="tab3">
      									
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
		</div><!--/.fluid-container-->	
		
		<%@include file="/static/footer.jsp"%>	
	  	<%@include file="/static/javascript_footer.jsp"%>
	  	
	</body>
</html>