package OrderController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import OrderBean.OrderBean;
import OrderDao.OrderDao;

@WebServlet("/InsertOrder")
public class InsertOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertOrder() {
		super();
	}

	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        try {
            // 連接資料庫
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");
            conn = ds.getConnection();

            // 設定請求和回應編碼
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            // 取得表單參數
            String customerId = request.getParameter("customerId");
            String caregiverId = request.getParameter("caregiverId");
            String orderDate = request.getParameter("orderDate");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String status = request.getParameter("status");
            String totalPrice = request.getParameter("totalPrice");

            // 檢查必填欄位是否存在
            if (customerId != null && caregiverId != null && status != null && totalPrice != null) {

                // 嘗試將顧客ID、看護ID、總價轉換為相應數字型別
                int customerIdInt = Integer.parseInt(customerId);
                int caregiverIdInt = Integer.parseInt(caregiverId);
                int totalPriceInt = Integer.parseInt(totalPrice);

                // 處理日期欄位轉換
                Date orderDateParsed = null;
                Date startDateParsed = null;
                Date endDateParsed = null;

                if (orderDate != null && !orderDate.isEmpty()) {
                    orderDateParsed = Date.valueOf(orderDate);  // 訂單日期轉換為 Date
                }
                if (startDate != null && !startDate.isEmpty()) {
                    startDateParsed = Date.valueOf(startDate);  // 開始日期轉換為 Date
                }
                if (endDate != null && !endDate.isEmpty()) {
                    endDateParsed = Date.valueOf(endDate);  // 結束日期轉換為 Date
                }

                // 創建 OrderBean 物件並設置屬性
                OrderBean order = new OrderBean();
                order.setCustomerId(customerIdInt);
                order.setCaregiverId(caregiverIdInt);
                order.setStatus(status);
                order.setTotalPrice(totalPriceInt);

                // 設置日期欄位，如果有值
                if (orderDateParsed != null) order.setOrderDate(orderDateParsed);
                if (startDateParsed != null) order.setStartDate(startDateParsed);
                if (endDateParsed != null) order.setEndDate(endDateParsed);

                // 呼叫 DAO 層新增訂單
                OrderDao orderDao = new OrderDao();
                boolean isInserted = orderDao.addOrder(order);

                // 根據結果顯示訊息
                if (isInserted) {
                    response.getWriter().write("訂單新增成功");
                } else {
                    response.getWriter().write("訂單新增失敗");
                }
            } else {
                response.getWriter().write("顧客ID、看護ID、狀態和總價為必填");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("新增訂單時發生錯誤");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
