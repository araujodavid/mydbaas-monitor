<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="author" content="David Araújo"/>
		<meta name="reply-to" content="araujodavid@lia.ufc.br"/>
		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">

		<title>MyDBaaSMonitor - Agent Configuration</title>
	</head>
	<body>		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container" style="margin-top:70px;">		
			<form method="post" action="${pageContext.servletContext.contextPath}/agent/send">
				<fieldset>
						<legend><strong>Setup Agent</strong></legend>
						<h5>Host: ${host.alias}</h5><br>
						<p class="text-warning">1. If the cycle of a metric is zero or empty it is considered disabled.</p>
						
						<c:if test="${host.status == true}">
							<br><p class="text-error">This host has an <strong>agent configured</strong>. If you continue the agent will be stopped and the new configuration sent.</p>
						</c:if>	
		  				
		  				<h4 class="text-info" style="margin-top:20px;">Machine Metrics:</h4>
		  				
		  				<input type="hidden" name="resourceID" value="${host.id}" />
		  				<input type="hidden" name="resourceType" value="host" />
		  						  				
		  				<div class="row">
  							<div class="span4">
  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
    								<h4>Cpu</h4>
    								<input type="hidden" name="profiles[0].name" value="cpu" />
    								<input type="hidden" name="profiles[0].type" value="host" />
    								<div class="input-append" style="margin-top:7px;">
    									<input class="span2" id="appendedInput" type="text" name="profiles[0].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
  										<span class="add-on"><i class="icon-repeat"></i></span>  										
									</div>									
    							</fieldset>
  							</div>
  							<div class="span4">  							
  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
    								<h4>Memory</h4>
    								<input type="hidden" name="profiles[1].name" value="memory" />
    								<input type="hidden" name="profiles[1].type" value="host" />
    								<div class="input-append" style="margin-top:7px;">
    									<input class="span2" id="appendedInput" type="text" name="profiles[1].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
  										<span class="add-on"><i class="icon-repeat"></i></span>  										
									</div>
    							</fieldset>
  							</div>
  							<div class="span4">
  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
    								<h4>Network</h4>
    								<input type="hidden" name="profiles[2].name" value="network" />
    								<input type="hidden" name="profiles[2].type" value="host" />
    								<div class="input-append" style="margin-top:7px;">
    									<input class="span2" id="appendedInput" type="text" name="profiles[2].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
  										<span class="add-on"><i class="icon-repeat"></i></span>  										
									</div>
    							</fieldset>
  							</div>
						</div>
						
						<div class="row" style="margin-top:20px;">
  							<div class="span4">
  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
    								<h4>Disk</h4>
    								<input type="hidden" name="profiles[3].name" value="disk" />
    								<input type="hidden" name="profiles[3].type" value="host" />
    								<div class="input-append" style="margin-top:7px;">
    									<input class="span2" id="appendedInput" type="text" name="profiles[3].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
  										<span class="add-on"><i class="icon-repeat"></i></span>  										
									</div>
    							</fieldset>
  							</div>
  							<div class="span4">
  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
    								<h4>Partition</h4>
    								<input type="hidden" name="profiles[4].name" value="partition" />
    								<input type="hidden" name="profiles[4].type" value="host" />
    								<div class="input-append" style="margin-top:7px;">
    									<input class="span2" id="appendedInput" type="text" name="profiles[4].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
  										<span class="add-on"><i class="icon-repeat"></i></span>  										
									</div>
    							</fieldset>
  							</div>
						</div>					
						
						<h4 class="text-info" style="margin-top:20px;">Host Metrics:</h4>
						
						<div class="row">
  							<div class="span4">
  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
    								<h4>Active and Inactive Domains</h4>
    								<input type="hidden" name="profiles[5].name" value="hostDomains" />
    								<input type="hidden" name="profiles[5].type" value="host" />
    								<div class="input-append" style="margin-top:7px;">
    									<input class="span2" id="appendedInput" type="text" name="profiles[5].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
  										<span class="add-on"><i class="icon-repeat"></i></span>  										
									</div>									
    							</fieldset>
  							</div>
  							<div class="span4">  							
  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
    								<h4>Domains Status</h4>
    								<input type="hidden" name="profiles[6].name" value="domainStatus" />
    								<input type="hidden" name="profiles[6].type" value="host" />
    								<div class="input-append" style="margin-top:7px;">
    									<input class="span2" id="appendedInput" type="text" name="profiles[6].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
  										<span class="add-on"><i class="icon-repeat"></i></span>  										
									</div>
    							</fieldset>
  							</div>
						</div>	  				
						
						<div class="form-actions">
							<button type="submit" class="btn btn-success">Start</button>
							<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/hosts/view/${host.id}" onclick="return confirm('Are you sure want to cancel the configuration?');">Cancel</a>
						</div>					  	
	  			</fieldset>
		 	</form>
	 	</div> <!-- /container -->
	 	
    	<%@include file="/static/footer.jsp"%>
	</body>
</html>