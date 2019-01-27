<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.util.List"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<div>


    		<form action="upload" method="post" enctype="multipart/form-data">
    			<input type="file" name="file" />
    			<input type="submit" value="upload" />
    		</form>

    	</div>
                <div id="result">
                    <h3>${requestScope["message"]}</h3>
                </div>

</body>
</html>