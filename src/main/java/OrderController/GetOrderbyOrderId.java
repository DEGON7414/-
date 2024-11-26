package OrderController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import OrderBean.OrderBean;
import OrderDao.OrderDao;

@WebServlet("/GetOrderByOrderId")
public class GetOrderbyOrderId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetOrderbyOrderId() {
		super();
	}
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // 確認接收到的訂單ID參數
	        String orderIdParam = request.getParameter("orderId");

	        // 打印接收到的參數，方便排錯
	        System.out.println("收到的訂單 ID 參數: " + orderIdParam);

	        // 檢查是否有傳入訂單 ID 參數
	        if (orderIdParam == null || orderIdParam.isEmpty()) {
	            // 如果沒有傳入有效的訂單 ID，顯示錯誤訊息
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            out.println("<html><body>");
	            out.println("<h2>錯誤：訂單 ID 參數缺失</h2>");
	            out.println("<p>請傳遞有效的訂單 ID。</p>");
	            out.println("</body></html>");
	            return;
	        }

	        try {
	            // 將 orderId 參數轉換為整數
	            int orderId = Integer.parseInt(orderIdParam);

	            // 從資料庫獲取訂單資料
	            OrderDao orderDao = new OrderDao();
	            OrderBean orderBean = orderDao.getOrderByOrderId(orderId);

	            // 設定回應格式為 HTML
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            out.println("<html><body>");

	            // 檢查是否找到了該訂單
	            if (orderBean != null) {
	                // 顯示訂單詳細資料
	                out.println("<h2>訂單詳細資訊</h2>");
	                out.println("<table border='1'>");
	                out.println("<tr><th>訂單ID</th><td>" + orderBean.getOrderId() + "</td></tr>");
	                out.println("<tr><th>客戶ID</th><td>" + orderBean.getCustomerId() + "</td></tr>");
	                out.println("<tr><th>照顧者ID</th><td>" + orderBean.getCaregiverId() + "</td></tr>");
	                out.println("<tr><th>訂單日期</th><td>" + orderBean.getOrderDate() + "</td></tr>");
	                out.println("<tr><th>開始日期</th><td>" + orderBean.getStartDate() + "</td></tr>");
	                out.println("<tr><th>結束日期</th><td>" + orderBean.getEndDate() + "</td></tr>");
	                out.println("<tr><th>狀態</th><td>" + orderBean.getStatus() + "</td></tr>");
	                out.println("<tr><th>總金額</th><td>" + orderBean.getTotalPrice() + "</td></tr>");
	                out.println("</table>");
	            } else {
	                // 如果找不到該訂單，顯示錯誤訊息
	                out.println("<h2>錯誤：找不到此訂單</h2>");
	                out.println("<p>此訂單 ID 的資料不存在，請檢查並重試。</p>");
	            }

	            out.println("</body></html>");
	        } catch (NumberFormatException e) {
	            // 如果無法將 orderId 參數轉換為整數，顯示錯誤訊息
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            out.println("<html><body>");
	            out.println("<h2>錯誤：訂單 ID 格式不正確</h2>");
	            out.println("<p>請確保訂單 ID 是有效的整數。</p>");
	            out.println("</body></html>");
	        }
	    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
