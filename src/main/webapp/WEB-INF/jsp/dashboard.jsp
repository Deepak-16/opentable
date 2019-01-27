<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.util.*"%>
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
<a class="fixed" href="/">Go to Uploads!</a>
	<table border=3>
		<tr>
			<th>Name</th>
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
</body>
</html>