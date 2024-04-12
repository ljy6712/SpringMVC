<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Total Credit by Year and Semester</title>
</head>
<body>
<h1>Total Credit by Year and Semester</h1>

<table border="1">
    <thead>
    <tr>
        <th>Year</th>
        <th>Semester</th>
        <th>Total Credit</th>
    </tr>
    </thead>
    <tbody>
    <%
        Map<String, Integer> totalCreditMap = (Map<String, Integer>) request.getAttribute("id_credits");
        for (Map.Entry<String, Integer> entry : totalCreditMap.entrySet()) {
            String[] keyParts = entry.getKey().split("-");
            String year = keyParts[0];
            String semester = keyParts[1];
            Integer totalCredit = entry.getValue();
    %>
    <tr>
        <td><%= year %></td>
        <td><%= semester %></td>
        <td><%= totalCredit %></td>
        <td><a href="${pageContext.request.contextPath}/detail/year=${year}&semester=${semester}">링크</a></td>
    </tr>
    <% } %>
    <%
        int totalCredits = (int) request.getAttribute("id_totalCredits");
    %>
    <tr>
        <td>총계</td>
        <td></td>
        <td><%= totalCredits %></td>
        <td></td>
    </tr>
    </tbody>
</table>
</body>
</html>
