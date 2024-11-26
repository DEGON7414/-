package OrderController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import OrderBean.OrderBean;
import OrderDao.OrderDao;

@WebServlet("/UpdateOrderByOrderId")
public class UpdateOrderByOrderId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateOrderByOrderId() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	        try {
	        	System.out.println("123");
	            // 解析參數
	            int orderId = Integer.parseInt(request.getParameter("orderId"));
	            int customerId = Integer.parseInt(request.getParameter("customerId"));
	            int caregiverId = Integer.parseInt(request.getParameter("caregiverId"));
	            String status = request.getParameter("status");
	            int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));

	            // 日期格式化
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date orderDate = sdf.parse(request.getParameter("orderDate"));
	            java.util.Date startDate = sdf.parse(request.getParameter("startDate"));
	            java.util.Date endDate = sdf.parse(request.getParameter("endDate"));

	            // 更新訂單
	            OrderBean updatedOrder = new OrderBean(orderId, customerId, caregiverId, orderDate, startDate, endDate, status, totalPrice);
	            boolean isUpdated = new OrderDao().updateOrder(updatedOrder);
	            
	            // 回應結果
	            if (isUpdated) {
	                response.getWriter().write("<p>訂單更新成功</p>");
	            } else {
	                response.getWriter().write("<p>訂單更新失敗</p>");
	            }
	        } catch (Exception e) {
	            response.getWriter().write("<p>請確認輸入資料格式</p>");
	        }
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
