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
.uploadedFiles {
  list-style-type: none;
  margin: 0;
  padding: 0;
  border: 1px solid #2c6da0;
}
li {
  border-bottom: 1px solid #2c6da0;
  padding: 10px ;
}
li#heading {
 background-color: #2c6da0;
  color: white;

}
.list-heading {
      font-weight: 800;
    margin-bottom: 0px;
      font-size: 20px;
}
.fixed {
  margin-bottom: 5px;
  display: inline-block;
}
</style>
<div class="uploadedDiv">
          <p class='list-heading'>Resized file list:</p>
          <a class="fixed" href="/">(Go to Upload Images)</a>
          <ul class='uploadedFiles'>
                   <li id='heading'>
                      File Name
                   </li>
             <%
                List<String> imgList = (List<String>) request
                                    .getAttribute("image_list");
                   for (String item : imgList) {
                %>
             <li>
                   <c:url value="/download" var="url">
                      <c:param name="path" value="<%=item%>" />
                   </c:url>
                   <a href="${url}"><%=item%></a>
             </li>
             <%
                }
                %>
          </ul>
       </div>
	</table>
</body>
</html>