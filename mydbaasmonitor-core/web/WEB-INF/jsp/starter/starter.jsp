<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Content-Language" content="pt-br" />		
		<link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
		<style type="text/css">
		      body {
		        padding-top: 40px;
		        padding-bottom: 40px;
		        background-color: #f5f5f5;
		      }
		
		      .form {
		        max-width: 300px;
		        padding: 19px 29px 29px;
		        margin: 0 auto 20px;
		        background-color: #fff;
		        border: 1px solid #e5e5e5;
		        -webkit-border-radius: 5px;
		           -moz-border-radius: 5px;
		                border-radius: 5px;
		        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
		           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
		                box-shadow: 0 1px 2px rgba(0,0,0,.05);
		      }
		      .form .form-heading {
		        margin-bottom: 10px;
		      }
		      .form input[type="text"],
		      .form input[type="password"] {
		        font-size: 16px;
		        height: auto;
		        margin-bottom: 15px;
		        padding: 7px 9px;
		      }
    	</style>
		<title>MyDBaaSMonitor - Startup</title>
	</head>
	<body>		
		<%@include file="/static/menu.jsp"%>
		
		<div class="container" style="width:670px; margin-top:70px;">
 			<c:if test="${notice != null}">							
				<div class="alert alert-success">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${notice}
				</div>		  				
 			</c:if>
 			<c:if test="${erro != null}">							
				<div class="alert alert-error">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${erro}
				</div>		  				
 			</c:if>
 			<c:if test="${errors != null}">							
				<c:forEach var="error" items="${errors}">
					<div class="alert alert-error">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<strong>${error.category}!</strong> ${error.message}
					</div>
  				</c:forEach>			  				
 			</c:if>
		
      		<form class="form" style="float:left;" method="post" action="<c:url value="/starter/database"/>">
       	 		<h2 class="form-heading">Access Database</h2>
        		<input name="schema" type="text" class="input-block-level" value="${pool.schema}" placeholder="Schema name" >
        		<input name="port" type="text" class="input-block-level" <c:if test="${pool.port > 0}">value="${pool.port}"</c:if> placeholder="Port" >
        		<input name="username" type="text" class="input-block-level" value="${pool.username}" placeholder="Username" >
        		<input name="password" type="password" class="input-block-level" value="${pool.password}" placeholder="Password" >
        		<button class="btn btn-inverse" type="submit" style="float:right;">Setup</button>
      		</form>
      		
      		<form class="form" style="float:right;" method="post" action="<c:url value="/starter/tables"/>">
       	 		<h2 class="form-heading">Start Database</h2>
       	 		<table class="table table-striped">
					<thead>
						<tr>
							<th>Table</th>
							<th>Status</th>
						</tr>
  					</thead>
  					<tbody>  						
       	 				<c:forEach items="${tables.keySet()}" var="table">
       	 					<tr>    								
								<td>
									${table}
								</td>
								<td>
			       	 				<c:choose>
			  							<c:when test="${tables.get(table) == true}">
			  								<i class="status-monitor-active" style="margin-left:18px;"></i>
			      						</c:when>
			      						<c:otherwise>
			  								<i class="status-monitor-inactive" style="margin-left:18px;"></i>
			  							</c:otherwise>
			 						</c:choose>
			 					</td>						
    						</tr>
       	 				</c:forEach>
       	 			</tbody>
				</table>
				<c:choose>
					<c:when test="${connectionOK == false}">
						<p class="text-info">Please configure access before!</p>
					</c:when>
					<c:otherwise>
						<button class="btn btn-inverse" type="submit" style="float:right;">Create</button>
					</c:otherwise>
				</c:choose>        		
      		</form>
    	</div> <!-- /container -->
    	
    	<%@include file="/static/footer.jsp"%>	
	  	<%@include file="/static/javascript_footer.jsp"%>
	</body>
</html>