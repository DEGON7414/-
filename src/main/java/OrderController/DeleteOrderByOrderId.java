package OrderController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.Console;
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

@WebServlet("/DeleteOrderByOrderId")
public class DeleteOrderByOrderId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteOrderByOrderId() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 設定回應的內容類型
        response.setContentType("text/plain; charset=UTF-8");

        // 獲取請求中的參數
        String orderIdParam = request.getParameter("orderId");
        System.out.println(request.getParameter("orderId"));
        System.out.println("123");
        if (orderIdParam == null || orderIdParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("缺少訂單編號");
            return;
        }

        try {
            // 將參數轉換為整數
            int orderId = Integer.parseInt(orderIdParam);

            // 使用 DAO 刪除訂單
            OrderDao orderDao = new OrderDao();
            boolean isDeleted = orderDao.deleteById(orderId);

            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("刪除成功");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("找不到該訂單");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("訂單編號必須為有效的數字");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("刪除過程中發生錯誤");
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
