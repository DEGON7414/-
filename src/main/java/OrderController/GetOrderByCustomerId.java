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

@WebServlet("/GetOrderByCustomerId")
public class GetOrderByCustomerId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetOrderByCustomerId() {
		super();
	}

	
    Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {String customerIdStr = request.getParameter("customerId");

	        // 檢查 customerId 是否為 null 或空值
	        if (customerIdStr != null && !customerIdStr.trim().isEmpty()) {
	            try {
	                // 連接資料庫
	                InitialContext context = new InitialContext();
	                DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");
	                conn = ds.getConnection();

	                // 取得根據 customerId 查詢的訂單列表
	                OrderDao orderDao = new OrderDao();
	                List<OrderBean> orders = orderDao.getOrderByCustomerId(Integer.parseInt(customerIdStr));

	                // 設定回應內容型別為 HTML
	                response.setContentType("text/html");
	                response.setCharacterEncoding("UTF-8");

	                // 生成訂單表格的 HTML
	                StringBuilder htmlResponse = new StringBuilder();
	                for (OrderBean order : orders) {
	                    htmlResponse.append("<tr>");
	                    htmlResponse.append("<td>").append(order.getOrderId()).append("</td>");
	                    htmlResponse.append("<td>").append(order.getCustomerId()).append("</td>");
	                    htmlResponse.append("<td>").append(order.getCaregiverId()).append("</td>");
	                    htmlResponse.append("<td>").append(order.getOrderDate()).append("</td>");
	                    htmlResponse.append("<td>").append(order.getStartDate()).append("</td>");
	                    htmlResponse.append("<td>").append(order.getEndDate()).append("</td>");
	                    htmlResponse.append("<td>").append(order.getStatus()).append("</td>");
	                    htmlResponse.append("<td>").append(order.getTotalPrice()).append("</td>");
	                    
	                    htmlResponse.append("<td><button class=\"updateButton\" data-orderid=\"")
                        .append(order.getOrderId())
                        .append("\">編輯</button>")
                        .append("<button class=\"deleteButton\" data-orderid=\"")
                        .append(order.getOrderId())
                        .append("\">刪除</button></td>");
	                    
	                    htmlResponse.append("</tr>");
	                }

	                // 回傳生成的 HTML 給前端
	                response.getWriter().write(htmlResponse.toString());

	            } catch (NamingException | SQLException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    if (conn != null) {
	                        conn.close();
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        } else {
	            // 如果沒有顧客編號，回傳錯誤或顯示所有訂單
	            response.getWriter().write("<p>請提供顧客編號進行查詢。</p>");
	        }
	    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
