<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>

    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
    
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
    
</head>

<body>

	<button id="cmd">Download PDF</button>
	<div id="content">
  		<h1>Contact Tracing Report</h1>
  		
  		<hr/>
  		
    	<h2>Student</h2>
    	<p>Matricola:  ${sourceStudent.matricola}</p>
    	<p>Email: ${sourceStudent.email}</p>
    	<p>First Name: ${sourceStudent.name}</p>
    	<p>Last Name: ${sourceStudent.surname}</p>
    	<p>Address: ${sourceStudent.address}</p>
    	<p>Date Of Birth: ${sourceStudent.dateOfBirth}</p>
    	
    	<hr/>
    	
    	<table>
    		<thead>
   				<tr>
				    <th>Matricola</th>
				    <th>Email</th>
				    <th>First Name</th>
				    <th>Last Name</th>
				    <th>Address</th>
				    <th>Date Of Birth</th>
				</tr>
				
				<c:forEach var="student" items="${contactedStudents}">
					<tr>
						<td>${student.matricola}</td>
						<td>${student.email}</td>
						<td>${student.name}</td>
						<td>${student.surname}</td>
						<td>${student.address}</td>
						<td>${student.dateOfBirth}</td>
					</tr>
				</c:forEach>
    		</thead>
    	
    	
    	</table>
    	
    	
	</div>
	
	<div id="editor"></div>
	

	<script>
		var doc = new jsPDF();
		var specialElementHandlers = {
		    '#editor': function (element, renderer) {
		        return true;
		    }
		};
		
		$('#cmd').click(function () {
		    doc.fromHTML($('#content').html(), 15, 15, {
		        'width': 170,
		            'elementHandlers': specialElementHandlers
		    });
		    doc.save('sample-file.pdf');
		});
	</script>
</body>

</html>