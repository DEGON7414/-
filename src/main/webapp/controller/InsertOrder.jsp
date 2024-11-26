<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增訂單</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
    <h2>新增訂單</h2>
    <form action="/InsertOrder" method="post">
        客戶編號: <input type="text" name="customerId" required /><br><br>
        看護編號: <input type="text" name="caregiverId" required /><br><br>
        訂單日期: <input type="date" name="orderDate" required /><br><br>
        開始日期: <input type="date" name="startDate" required /><br><br>
        結束日期: <input type="date" name="endDate" required /><br><br>
        訂單狀態: <input type="text" name="status" required /><br><br>
        總價: <input type="number" name="totalPrice" required /><br><br>
        <input type="submit" value="提交訂單" />
    </form>

    <%-- 顯示訊息 --%>
    <div style="color: red; margin-top: 20px;">
        <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
    </div>
</div>
</body>
</html>