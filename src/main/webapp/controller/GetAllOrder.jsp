<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="OrderBean.OrderBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>訂單資料</title>
</head>
<body style="background-color:#fdf5e6">

<div align="center">
    <h2>所有訂單資料</h2>

    <%-- 直接從 request 中取得 "orders" 屬性 --%>
    <%
        List<OrderBean> orderList = (List<OrderBean>) request.getAttribute("orders");
        if (orderList != null && !orderList.isEmpty()) {
    %>

    <table border="1">
        <tr>
            <th>訂單ID</th>
            <th>顧客ID</th>
            <th>看護ID</th>
            <th>訂單狀態</th>
            <th>訂單日期</th>
            <th>開始日期</th>
            <th>結束日期</th>
            <th>總價</th>
        </tr>
        
        <%-- 迭代顯示所有訂單 --%>
        <%
            for (OrderBean order : orderList) {
        %>
        <tr>
            <td><%= order.getOrderId() %></td>
            <td><%= order.getCustomerId() %></td>
            <td><%= order.getCaregiverId() %></td>
            <td><%= order.getStatus() %></td>
            <td><%= order.getOrderDate() %></td>
            <td><%= order.getStartDate() %></td>
            <td><%= order.getEndDate() %></td>
            <td><%= order.getTotalPrice() %></td>
        </tr>
        <%
            }
        %>
    </table>

    <%
        } else {
    %>
    <h3>無任何訂單資料</h3>
    <%
        }
    %>

</div>

</body>
</html>