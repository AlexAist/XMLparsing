<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Banks</title>
</head>
<body>
<table class = "table_blur" border="2px" align="center">
    <caption>List of Banks</caption>
    <tr>
        <th rowspan="2">Id</th>
        <th rowspan="2">Name</th>
        <th rowspan="2">Country</th>
        <th rowspan="2">Type</th>
        <th rowspan="2">Account id</th>
        <th rowspan="2">Amount on deposit</th>
        <th rowspan="2">Profitability</th>
        <th rowspan="2">Time constrains</th>
        <th rowspan="2">Create date</th>
        <th rowspan="2">Depositor</th>
        <th rowspan="2">Status</th>
    </tr>
    <tr>

    </tr>

    <c:forEach var="bank" items="${banks}">
        <tr>
            <td>${bank.getId()}</td>
            <td>${bank.getName()}</td>
            <td>${bank.getCountry()}</td>
            <td>${bank.getType()}</td>
            <td>${bank.getAccountId()}</td>
            <td>${bank.getAmountOnDeposit()}</td>
            <td>${bank.getProfitability()}</td>
            <td>${bank.getTimeConstraints()}</td>
            <td>${bank.getCreateDate()}</td>
            <td>${bank.getDepositor().getName()}
                    ${bank.getDepositor().getSurname()}</td>
            <c:choose>
                <c:when
                        test="${bank.getClass().name == 'com.epam.xml.entity.StateBank'}">
                    <td>${bank.getStatus()}</td>
                </c:when>
                <c:otherwise>
                    <td>-</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
<a href="/XMLparsing_war_exploded">Back</a>
</body>
</html>