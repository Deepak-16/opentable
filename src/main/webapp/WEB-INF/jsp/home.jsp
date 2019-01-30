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
#mainContainer {
  text-align: center
}

.upload-btn-wrapper {
  position: relative;
  overflow: hidden;
  margin-bottom:20px;
}

.btn {
  border: 2px solid gray;
  color: gray;
  background-color: white;
  padding: 8px 20px;
  font-size: 20px;
  font-weight: bold;
}

.upload-btn-wrapper input[type=file] {
  font-size: 100px;
  position: absolute;
  left: 0;
  top: 0;
  opacity: 0;
}

input[type='submit'] {
      font-size: 20px;
    color: white;
    padding: 8px 20px;
  background-color: #2c6da0;
  border-radius : 2px;
  border: none;
}
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
#result {
  font-size:14px;
}
#uploadDiv {
      border: 2px solid #2c6da0;
  padding: 10px;
}
.fixed {
  margin-bottom: 5px;
  display: inline-block;
}
</style>
	<div id='mainContainer'>

       <div id="uploadDiv">
          <h3>Upload file:</h3>
          <form action="upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
             <input type="submit" value="Upload">
          </form>
          <p id="result">
           <c:set var = "msg" value = '<%=request.getAttribute("message")%>'/>
           <c:if test = "${not empty msg}">
              <%=request.getAttribute("message")%>
           </c:if>
          </div>
       </div>
       <div class="uploadedDiv">
          <p class='list-heading'>Original Uploaded file list:</p>
          <a class="fixed" href="/list">(Go to Resized Images)</a>
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
    </div>

</body>
</html>