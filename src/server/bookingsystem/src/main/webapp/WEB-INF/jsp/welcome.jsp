<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>

    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
    
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
	<script type="text/javascript" src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>
    
</head>

<body>

	<button id="pdfBtn" type="button">Download as PDF</button>
	<button id="csvBtn">Download as CSV</button>
	
	<div id="content">
    	<div class = "html-content">
	  		<h1>Contact Tracing Report</h1>
	  		
	  		<hr/>
	  		
	    	<h2>${sourceType}</h2>
      		
      		<c:choose>
      		   <c:when test = "${sourceType == 'Student'}"><p>Matricola:  ${source.matricola}</p></c:when>
      		   <c:otherwise><p>Code:  ${source.code}</p></c:otherwise> 
      		</c:choose>
      		

	    	<p>Email: ${source.email}</p>
	    	<p>First Name: ${source.name}</p>
	    	<p>Last Name: ${source.surname}</p>
	    	<p>Address: ${source.address}</p>
	      	<c:choose>
      		   <c:when test = "${sourceType == 'Student'}"><p>Date Of Birth: ${source.dateOfBirth}</p></c:when>
      		</c:choose>
	    	
	    	<hr/>
	    	<h1>Student(s)</h1>
	    	<table id="traceReport">
	    		<thead>
	   				<tr>
					    <th>Matricola</th>
					    <th>Email</th>
					    <th>First Name</th>
					    <th>Last Name</th>
					    <th>Address</th>
					</tr>
					
					<c:forEach var="student" items="${contactedStudents}">
						<tr>
							<td>${student.matricola}</td>
							<td>${student.email}</td>
							<td>${student.name}</td>
							<td>${student.surname}</td>
							<td>${student.address}</td>
						</tr>
					</c:forEach>
	    		</thead>
	    	</table>
	    	
	    	<hr/>
	    	<h1>Professor(s)</h1>
	    	<table id="traceReport">
	    		<thead>
	   				<tr>
					    <th>Code</th>
					    <th>Email</th>
					    <th>First Name</th>
					    <th>Last Name</th>
					    <th>Address</th>
					</tr>
					
					<c:forEach var="professor" items="${contactedProfessors}">
						<tr>
							<td>${professor.code}</td>
							<td>${professor.email}</td>
							<td>${professor.name}</td>
							<td>${professor.surname}</td>
							<td>${professor.address}</td>
						</tr>
					</c:forEach>
	    		</thead>
	    	</table>
	    </div>
    	
    	
	</div>
	
	<div id="editor"></div>
	
	<script>
	//Create PDf from HTML...
		function CreatePDFfromHTML() {
		    var HTML_Width = $(".html-content").width();
		    var HTML_Height = $(".html-content").height();
		    var top_left_margin = 15;
		    var PDF_Width = HTML_Width + (top_left_margin * 2);
		    var PDF_Height = (PDF_Width * 1.5) + (top_left_margin * 2);
		    var canvas_image_width = HTML_Width;
		    var canvas_image_height = HTML_Height;
	
		    var totalPDFPages = Math.ceil(HTML_Height / PDF_Height) - 1;
	
		    html2canvas($(".html-content")[0]).then(function (canvas) {
		        var imgData = canvas.toDataURL("image/jpeg", 1.0);
		        var pdf = new jsPDF('p', 'pt', [PDF_Width, PDF_Height]);
		        pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin, canvas_image_width, canvas_image_height);
		        for (var i = 1; i <= totalPDFPages; i++) { 
		            pdf.addPage(PDF_Width, PDF_Height);
		            pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4),canvas_image_width,canvas_image_height);
		        }
		        pdf.save("export_traceReport"+new Date().toLocaleDateString()+".pdf");
		    });
		}
	
		function download_table_as_csv(table_id, separator = ',') {
		    // Select rows from table_id
		    var rows = document.querySelectorAll('table#' + table_id + ' tr');
		    // Construct csv
		    var csv = [];
		    for (var i = 0; i < rows.length; i++) {
		        var row = [], cols = rows[i].querySelectorAll('td, th');
		        for (var j = 0; j < cols.length; j++) {
		            // Clean innertext to remove multiple spaces and jumpline (break csv)
		            var data = cols[j].innerText.replace(/(\r\n|\n|\r)/gm, '').replace(/(\s\s)/gm, ' ')
		            // Escape double-quote with double-double-quote (see https://stackoverflow.com/questions/17808511/properly-escape-a-double-quote-in-csv)
		            data = data.replace(/"/g, '""');
		            // Push escaped string
		            row.push('"' + data + '"');
		        }
		        csv.push(row.join(separator));
		    }
		    var csv_string = csv.join('\n');
		    // Download it
		    var filename = 'export_' + table_id + '_' + new Date().toLocaleDateString() + '.csv';
		    var link = document.createElement('a');
		    link.style.display = 'none';
		    link.setAttribute('target', '_blank');
		    link.setAttribute('href', 'data:text/csv;charset=utf-8,' + encodeURIComponent(csv_string));
		    link.setAttribute('download', filename);
		    document.body.appendChild(link);
		    link.click();
		    document.body.removeChild(link);
		}
		
		
		$('#pdfBtn').click(function () {
			CreatePDFfromHTML();
		});
		
		$('#csvBtn').click(function () {
			download_table_as_csv('traceReport');
		});
	</script>
</body>

</html>