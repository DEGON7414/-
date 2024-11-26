<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新訂單</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>更新訂單</h2>
<form action="/Order/UpdateOrderByOrderId" method="post">

輸入訂單ID : <input type="text" name="orderId" /><p>
輸入顧客ID : <input type="text" name="customerId" /><p>
輸入看護人ID : <input type="text" name="caregiverId" /><p>
輸入訂單日期 : <input type="text" name="orderDate" /><p>
輸入開始日期 : <input type="text" name="startDate" /><p>
輸入結束日期 : <input type="text" name="endDate" /><p>
輸入訂單狀態 : <input type="text" name="status" /><p>
輸入總價 : <input type="text" name="totalPrice" /><p>
<input type="submit" value="確定" />

</form>
</div>
</body>
</html>
