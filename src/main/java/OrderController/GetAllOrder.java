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
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import OrderBean.OrderBean;
import OrderDao.OrderDao;

@WebServlet("/GetAllOrder")
public class GetAllOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetAllOrder() {
		super();
	}

	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 InitialContext context;
	        try {
	            // 連接資料庫
	            context = new InitialContext();
	            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");
	            conn = ds.getConnection();

	            // 從資料庫取得所有訂單
	            OrderDao orderDao = new OrderDao();
	            List<OrderBean> orders = orderDao.getAllOrder();
	            // 設定回應內容型別為 HTML
	            response.setContentType("text/html");
	            response.setCharacterEncoding("UTF-8");
	            
	            //System.out.println("Total Orders: " + orders.size());
	            // 生成訂單表格的 HTML
	            StringBuilder htmlResponse = new StringBuilder();
//	            htmlResponse.append("<table>");
//	            htmlResponse.append("<thead><tr>");
//	            htmlResponse.append("<th>訂單編號</th>");
//	            htmlResponse.append("<th>客戶編號</th>");
//	            htmlResponse.append("<th>看護編號</th>");
//	            htmlResponse.append("<th>訂單日期</th>");
//	            htmlResponse.append("<th>開始日期</th>");
//	            htmlResponse.append("<th>結束日期</th>");
//	            htmlResponse.append("<th>狀態</th>");
//	            htmlResponse.append("<th>總價</th>");
//	            htmlResponse.append("<th>操作</th>");
//	            htmlResponse.append("</tr></thead>");
//	            htmlResponse.append("<tbody>");
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
	             // 注意這裡，將 orderId 傳遞到 data-orderId 屬性
	                htmlResponse.append("<td><button class=\"updateButton\" data-orderid=\"")
	                            .append(order.getOrderId())
	                            .append("\">編輯</button>")
	                            .append("<button class=\"deleteButton\" data-orderid=\"")
	                            .append(order.getOrderId())
	                            .append("\">刪除</button></td>");
	            }
	            htmlResponse.append("</tbody>");
//	            htmlResponse.append("</table>");

	            // 回傳 HTML 到前端
	            response.getWriter().write(htmlResponse.toString());

	        } catch (NamingException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}