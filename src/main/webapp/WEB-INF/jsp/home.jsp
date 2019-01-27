<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.util.List"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<style>
a.fixed {
    position: fixed;
    right: 0;
    top: 0;
    width: 260px;
    border: 3px solid #73AD21;
}
</style>
	<div>
	<div id="result">
	<c:set var = "msg" value = '<%=request.getAttribute("message")%>'/>
          <c:if test = "${not empty msg}">
              <h3><%=request.getAttribute("message")%></h3>
          </c:if>

                    </div>
            <div id="uploadDiv">
                                <h3>Upload file:</h3>


    		<form action="upload" method="post" enctype="multipart/form-data">
    			<input type="file" name="file" />
    			<input type="submit" value="upload" />
    		</form>
    		</div>
    		<a class="fixed" href="/list">Go to Resized Images!</a>
            <div id="uploadDiv">
                                <h3>Original Uploaded file list:</h3>
    		<table border=3>
            		<tr>
            			<th>File Name</th>
            		</tr>
            		<%
            		List<String> imgList = (List<String>) request
                    						.getAttribute("image_list");
            			for (String item : imgList) {
            		%>
            		<tr>
            			<td><c:url value="/download" var="url">
                              <c:param name="path" value="<%=item%>" />
                            </c:url>
                            <a href="${url}"><%=item%></a>
            			</td>
            		</tr>

            		<%
            			}
            		%>
            	</table>
</div>
    	</div>

</body>
</html>