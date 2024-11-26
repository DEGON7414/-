package OrderController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import OrderBean.OrderBean;
import OrderDao.OrderDao;

@WebServlet("/GetOrderByCaregiver")
public class GetOrderByCaregiver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetOrderByCaregiver() {
		super();
	}

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 獲取 customerId 參數
        String customerIdStr = request.getParameter("caregiverId");

        if (customerIdStr != null && !customerIdStr.isEmpty()) {
            try {
                int customerId = Integer.parseInt(customerIdStr);  // 轉換為 int

                OrderDao orderDao = new OrderDao();
                // 根據顧客ID查詢訂單，這裡返回的是一個 List
                List<OrderBean> orders = orderDao.getOrderByCustomerId(customerId);
                
                // 如果找到訂單，則轉發到 JSP 顯示
                if (orders != null && !orders.isEmpty()) {
                    request.setAttribute("orders", orders);
                    request.setAttribute("orderCount", orders.size());  // 傳遞訂單數量

                } else {
                    // 如果沒找到訂單，顯示錯誤訊息
                    request.setAttribute("message", "未找到該顧客的訂單");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "資料庫連接錯誤");
            }
        } else {
            request.setAttribute("message", "請提供有效的顧客ID");
        }

        // 無論結果如何，統一轉發到 JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/controller/GetAllOrder.jsp");
        dispatcher.forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
