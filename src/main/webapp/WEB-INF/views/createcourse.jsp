<%--
  Created by IntelliJ IDEA.
  User: nykim
  Date: 2024-03-11
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/main.css" >

</head>
<body>
<sf:form method="post" action="${pageContext.request.contextPath}/docreate" modelAttribute="offer">
    <table class="formtable">
        <tr>
            <td class="label"> 수강년도:</td>
            <td><sf:input class="control" type="number" path="yaer"/>  <br/>
                <sf:errors path="year" class="error"/>
            </td>
        </tr>
        <tr>
            <td class="label"> 학기:</td>
            <td><sf:input class="control" type="number" path="semester"/>  <br/>
                <sf:errors path="semester" class="error"/>
            </td>
        </tr>
        <tr>
            <td class="label"> 교과코드:</td>
            <td><sf:input class="control" type="text" path="code"/>  <br/>
                <sf:errors path="code" class="error"/>
            </td>
        </tr>
        <tr>
            <td class="label"> 교과구분:</td>
            <td><sf:input class="control" type="text" path="type"/> <br/>
                <sf:errors path="type" class="error" />
            </td>
        </tr>
        <tr>
            <td class="label"> 담당교수:</td>
            <td><sf:textarea class="control" rows="10" cols="10" path="professor"/> <br/>
                <sf:errors path="professor" class="error" />
            </td>
        </tr>
        <tr>
            <td class="label"> 학점:</td>
            <td><sf:textarea class="control" type="number" path="credit"/> <br/>
                <sf:errors path="credit" class="error" />
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="수강신청"/> </td>
        </tr>
    </table>
</sf:form>
</body>
</html>
