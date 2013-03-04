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
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>
    	<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>

		<title>MyDBaaSMonitor</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container-fluid">
    		<div class="row-fluid">
        		<div class="span3">
					<div align="left" style="margin-bottom:10px;">
        				<a class="btn btn-inverse" href="${pageContext.servletContext.contextPath}/vms/new" title=""><i class="icon-plus icon-white"></i> Machine</a>
        			</div>
		            <div class="accordion" id="accordion2">
						<div class="accordion-group">
							<div class="accordion-heading">
  								<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
    								<i class="icon-tasks" style="margin-right:5px;" title="List of active virtual machines."></i>Virtual Machines
 	 							</a>
							</div>
							<div id="collapseOne" class="accordion-body collapse in">								
								<c:forEach items="${listResources}" var="resource">
									<div class="accordion-inner">
										<a href="${pageContext.servletContext.contextPath}/vms/view/${ resource.id }"><i class="icon-tag"></i> ${ resource.alias }</a> [${ resource.host }]        									
  									</div>
								</c:forEach>
								<div class="accordion-inner">
									<a href="${pageContext.servletContext.contextPath}/vms/list"><i class="icon-tags"></i> All machines</a>        									
  								</div>   								
							</div>
						</div>
						<div class="accordion-group">
							<div class="accordion-heading">
  								<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
    								<i class="icon-list-alt" style="margin-right:6px;"></i>Databases
  								</a>
							</div>
							<div id="collapseTwo" class="accordion-body collapse">
							    <div class="accordion-inner">
							    	TODO!
								</div>
							</div>
						</div>
					</div>
          			
        		</div><!--/span-->
        		<div class="span9">
            		<div class="hero-unit">
                		<h1>Hello, world!</h1>
                		<p>This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
                		<p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>
            		</div>
		            <div class="row-fluid">
		                <div class="span4">
		                    <h2>Heading</h2>
		                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
		                    <p><a class="btn" href="#">View details &raquo;</a></p>
		                </div><!--/span-->
		                <div class="span4">
		                    <h2>Heading</h2>
		                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
		                    <p><a class="btn" href="#">View details &raquo;</a></p>
		                </div><!--/span-->
		                <div class="span4">
		                    <h2>Heading</h2>
		                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
		                    <p><a class="btn" href="#">View details &raquo;</a></p>
		                </div><!--/span-->
		            </div><!--/row-->          
        		</div><!--/span-->
    		</div><!--/row-->
    		
    		<%@include file="/static/footer.jsp"%>

		</div><!--/.fluid-container-->	 	
	  	
	</body>
</html>