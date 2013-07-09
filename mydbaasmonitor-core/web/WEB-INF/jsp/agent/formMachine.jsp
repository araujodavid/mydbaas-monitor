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
						<h5>Resource: ${machine.alias}</h5><br>
						<p class="text-warning">1. If the cycle of a metric is zero or empty it is considered disabled.</p>
						<p class="text-warning">2. When you set the cycle of a metric of DBMS or database check whether you really selected some resource.</p>
						
						<c:if test="${machine.status == true}">
							<br><p class="text-error">This virtual machine has an <strong>agent configured</strong>. If you continue the agent will be stopped and the new configuration sent.</p>
						</c:if>
		  				
		  				<h4 class="text-info" style="margin-top:20px;">Virtual Machine Metrics:</h4>
		  				
		  				<input type="hidden" name="resourceID" value="${machine.id}" />
		  				<input type="hidden" name="resourceType" value="machine" />
		  						  				
		  				<div class="row">
  							<div class="span4">
  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
    								<h4>Cpu</h4>
    								<input type="hidden" name="profiles[0].name" value="cpu" />
    								<input type="hidden" name="profiles[0].type" value="machine" />
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
    								<input type="hidden" name="profiles[1].type" value="machine" />
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
    								<input type="hidden" name="profiles[2].type" value="machine" />
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
    								<input type="hidden" name="profiles[3].type" value="machine" />
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
    								<input type="hidden" name="profiles[4].type" value="machine" />
    								<div class="input-append" style="margin-top:7px;">
    									<input class="span2" id="appendedInput" type="text" name="profiles[4].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
  										<span class="add-on"><i class="icon-repeat"></i></span>  										
									</div>
    							</fieldset>
  							</div>
						</div>					
						
						<c:if test="${machine.dbmsList != null && !machine.dbmsList.isEmpty()}">
							<h4 class="text-info" style="margin-top:30px;">DBMS and Database Metrics:</h4>						
							<div class="row">
	  							<div class="span4">
	  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
	    								<h4>Process Status</h4>
	    								<input type="hidden" name="profiles[5].name" value="processStatus" />
	    								<input type="hidden" name="profiles[5].type" value="storage" />
	    								<div class="input-append" style="margin-top:7px;">
	    									<input class="span2" id="appendedInput" type="text" name="profiles[5].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
	  										<span class="add-on"><i class="icon-repeat"></i></span>  										
										</div>
										<h5 style="margin-top:10px;">DBMSs Available:</h5>
										<c:forEach var="dbms" items="${machine.dbmsList}">
											<c:set var="count1" value="${count1 + 1}"/>
											<label class="checkbox">										  	
											  	${dbms.alias}
											  	<input type="checkbox" name="profiles[5].dbms[${count1-1}]" value="${dbms.id}">
											</label>
										</c:forEach>									
	    							</fieldset>
	  							</div>
	  							<div class="span4">  							
	  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
	    								<h4>Active Connections</h4>
	    								<input type="hidden" name="profiles[6].name" value="activeConnection" />
	    								<input type="hidden" name="profiles[6].type" value="storage" />
	    								<div class="input-append" style="margin-top:7px;">
	    									<input class="span2" id="appendedInput" type="text" name="profiles[6].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
	  										<span class="add-on"><i class="icon-repeat"></i></span>  										
										</div>
										<h5 style="margin-top:10px;">DBMSs Available:</h5>
										<c:forEach var="dbms" items="${machine.dbmsList}">
											<c:set var="count2" value="${count2 + 1}"/>
											<label class="checkbox">										  	
											  	${dbms.alias}
											  	<input type="checkbox" name="profiles[6].dbms[${count2-1}]" value="${dbms.id}">
											</label>
										</c:forEach>
										<h5 style="margin-top:10px;">Databases Available:</h5>
										<c:forEach var="dbms" items="${machine.dbmsList}">
											<c:forEach var="database" items="${dbms.databases}">
												<c:set var="count3" value="${count3 + 1}"/>
												<label class="checkbox">										  	
												  	${database.alias}
												  	<input type="checkbox" name="profiles[6].databases[${count3-1}]" value="${database.id}">
												</label>
											</c:forEach>
										</c:forEach>
	    							</fieldset>
	  							</div>
	  							<div class="span4">
	  								<fieldset style="padding-left: 5px; padding-bottom: 5px; border: 1px solid #eee;">
	    								<h4>Size</h4>
	    								<input type="hidden" name="profiles[7].name" value="size" />
	    								<input type="hidden" name="profiles[7].type" value="storage" />
	    								<div class="input-append" style="margin-top:7px;">
	    									<input class="span2" id="appendedInput" type="text" name="profiles[7].cycle" placeholder="Cycle in seconds" title="Cycle in seconds">
	  										<span class="add-on"><i class="icon-repeat"></i></span>  										
										</div>
										<h5 style="margin-top:10px;">DBMSs Available:</h5>
										<c:forEach var="dbms" items="${machine.dbmsList}">
											<c:set var="count4" value="${count4 + 1}"/>
											<label class="checkbox">										  	
											  	${dbms.alias}
											  	<input type="checkbox" name="profiles[7].dbms[${count4-1}]" value="${dbms.id}">
											</label>
										</c:forEach>
										<h5 style="margin-top:10px;">Databases Available:</h5>
										<c:forEach var="dbms" items="${machine.dbmsList}">
											<c:forEach var="database" items="${dbms.databases}">
												<c:set var="count5" value="${count5 + 1}"/>
												<label class="checkbox">										  	
												  	${database.alias}
												  	<input type="checkbox" name="profiles[7].databases[${count5-1}]" value="${database.id}">
												</label>
											</c:forEach>
										</c:forEach>
	    							</fieldset>
	  							</div>
							</div>
						</c:if>		  				
						
						<div class="form-actions">
							<button type="submit" class="btn btn-success">Start</button>
							<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/vms/view/${machine.id}" onclick="return confirm('Are you sure want to cancel the configuration?');">Cancel</a>
						</div>					  	
	  			</fieldset>
		 	</form>
	 	</div> <!-- /container -->
	 	
    	<%@include file="/static/footer.jsp"%>
	</body>
</html>