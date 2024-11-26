<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>刪除訂單</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>刪除訂單</h2>
<jsp:useBean id="order" scope="request" class="OrderBean.OrderBean"/>
<table>

<tr>
    <td>訂單 ID</td>
    <td><input type="text" disabled value="<%= order.getOrderId() %>"></td>
</tr>
<tr>
    <td>顧客 ID</td>
    <td><input type="text" disabled value="<%= order.getCustomerId() %>"></td>
</tr>
<tr>
    <td>看護 ID</td>
    <td><input type="text" disabled value="<%= order.getCaregiverId() %>"></td>
</tr>
<tr>
    <td>訂單日期</td>
    <td><input type="text" disabled value="<%= order.getOrderDate() %>"></td>
</tr>
<tr>
    <td>開始日期</td>
    <td><input type="text" disabled value="<%= order.getStartDate() %>"></td>
</tr>
<tr>
    <td>結束日期</td>
    <td><input type="text" disabled value="<%= order.getEndDate() %>"></td>
</tr>
<tr>
    <td>狀態</td>
    <td><input type="text" disabled value="<%= order.getStatus() %>"></td>
</tr>
<tr>
    <td>總金額</td>
    <td><input type="text" disabled value="<%= order.getTotalPrice() %>"></td>
</tr>

</table>

</form>
</div>
</body>
</html>