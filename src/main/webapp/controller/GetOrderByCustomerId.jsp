<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="OrderBean.OrderBean" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>訂單資料</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>顯示訂單資料</h2>
<%-- 顯示顧客ID和訂單數量 --%>
<%
    String customerId = request.getParameter("customerId");
    Integer orderCount = (Integer) request.getAttribute("orderCount");
    if (customerId != null && orderCount != null) {
        out.println("<h3>顧客ID: " + customerId + "，共有 " + orderCount + " 筆訂單</h3>");
    }
%>

<%-- 直接從 request 中取得 "orders" 屬性 --%>
<%
    List<OrderBean> orderList = (List<OrderBean>) request.getAttribute("orders");
    if (orderList != null && !orderList.isEmpty()) {
        for (OrderBean order : orderList) {
%>
<table border="1">
    <tr><td>顧客ID</td><td><input type="text" disabled value="<%= order.getCustomerId() %>"></td></tr>
    <tr><td>訂單ID</td><td><input type="text" disabled value="<%= order.getOrderId() %>"></td></tr>
    <tr><td>看護ID</td><td><input type="text" disabled value="<%= order.getCaregiverId() %>"></td></tr>
    <tr><td>訂單狀態</td><td><input type="text" disabled value="<%= order.getStatus() %>"></td></tr>
    <tr><td>訂單日期</td><td><input type="text" disabled value="<%= order.getOrderDate() %>"></td></tr>
    <tr><td>開始日期</td><td><input type="text" disabled value="<%= order.getStartDate() %>"></td></tr>
    <tr><td>結束日期</td><td><input type="text" disabled value="<%= order.getEndDate() %>"></td></tr>
    <tr><td>總價</td><td><input type="text" disabled value="<%= order.getTotalPrice() %>"></td></tr>
</table>
<% 
        }
    } else {
%>
    <h3>無符合的訂單資料</h3>
<% 
    }
%>

<%-- 表單讓使用者輸入顧客ID查詢訂單 --%>
<form method="post" action="/Order/GetOrderByCustomerId">
    輸入顧客ID: <input type="text" name="customerId" /><br />
    <input type="submit" value="查詢" />
</form>

</div>

</body>
</html>