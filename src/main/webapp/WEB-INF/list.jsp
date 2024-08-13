<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>	
	
<%
	List<PersonVo> personList= (List<PersonVo>)request.getAttribute("personList");
	System.out.println("여기는 jsp");
	System.out.println(personList);
%>	
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>전화번호부-리스트</title>
</head>
<body>
	<h1>전화번호부</h1>
	
	<h2>전화번호-리스트</h2>
	
	<p>등록된 전화번호 리스트 입니다.</p>
	<%for(int i = 0; i<personList.size(); i++){ %>
	<table border="1">
		<tbody>
			<tr>
				<th>이름(name)</th>
				<td><%=personList.get(i).getName()%></td>
			</tr>
			<tr>
				<th>핸드폰(hp)</th>
				<td><%=personList.get(i).getHp()%></td>
			</tr>
			<tr>
				<th>회사(company)</th>
				<td><%=personList.get(i).getCompany()%></td>
			</tr>
			<tr>
				<th>[수정폼으로 이동]</th>
				<td>[삭제]</td>
			</tr>
		</tbody>
	</table>
	<br>
	<%} %>
	<br>
	<a href="">리스트로 가기</a>
</body>
</html>