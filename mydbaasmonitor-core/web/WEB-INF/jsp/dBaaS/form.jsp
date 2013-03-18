<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="author" content="David Araújo"/>
		<meta name="reply-to" content="araujodavid@lia.ufc.br"/>
		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>
    	<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>

		<title>MyDBaaSMonitor</title>
	</head>
	<body>
		
		<%@include file="/static/menu.jsp"%>

		<div class="container" style="margin-top:70px;">		
			<form method="post" action="${pageContext.servletContext.contextPath}/dbaas/add">
				<fieldset>
					<legend><strong>New Database-as-a-Service</strong></legend>
					
					<c:if test="${errors != null}">							
						<c:forEach var="error" items="${errors}">
							<div class="alert alert-error">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>${error.category}!</strong> ${error.message}
							</div>
						</c:forEach>			  				
					</c:if>
		 				
					<label class="text-info" for="alias">Alias:</label>
					<input class="input-xlarge" name="entity.alias" id="alias" type="text" value="${entity.alias}" placeholder="To better identify the DBaaS environment." />
					<span class="help-block"><em><small>Example: DBaaS Project Alpha</small></em></span>
				
					<label class="text-info" for="description">Description:</label>	  							
					<textarea name="entity.description" id="description" rows="3" style="margin-left:0px; margin-right:0px; width:399px;" >${entity.description}</textarea>
					
					<label class="text-info" for="recordDate">Record Date:</label>
					<input class="input-small" name="entity.recordDate" id="recordDate" type="text" readonly="readonly" value="${current_date}" />							
					
					<div class="form-actions">
						<button type="submit" class="btn btn-success">Save</button>
						<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/dbaas/list" onclick="return confirm('Are you sure want to cancel the registration?');">Cancel</a>
					</div>
				  	
				</fieldset>
		 	</form>
		</div> <!-- /container -->
		
		<%@include file="/static/footer.jsp"%>
	 	
	</body>
</html>