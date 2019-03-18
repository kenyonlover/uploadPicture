<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/imgup/getData">
		<img src="data:image/jpg;base64,"+${"img"} id="idPic"/>
		<input type="submit" value="获取">
	</form>
</body>
</html>