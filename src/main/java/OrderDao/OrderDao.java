package OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import OrderBean.OrderBean;

public class OrderDao {
	Connection conection = null;

//新增
	public boolean addOrder(OrderBean order) {
		String sql = "INSERT INTO dbo.Orders(customer_id, caregiver_id, order_date, start_date, end_date, status, total_price)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		Connection conection = null;
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");
			conection = ds.getConnection();
			preparedStatement = conection.prepareStatement(sql);
			preparedStatement.setInt(1, order.getCustomerId());
			preparedStatement.setInt(2, order.getCaregiverId());
			preparedStatement.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
			preparedStatement.setTimestamp(4, new Timestamp(order.getStartDate().getTime()));
			preparedStatement.setTimestamp(5, new Timestamp(order.getEndDate().getTime()));
			preparedStatement.setString(6, order.getStatus());
			preparedStatement.setInt(7, order.getTotalPrice());
			preparedStatement.executeUpdate();
			System.out.println("增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("失敗");
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (conection != null)
					conection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

//刪除訂單BY ID
	public boolean deleteById(Integer order_id) {
		String sql = "DELETE FROM Orders WHERE order_id=?";
		PreparedStatement preparedStatement = null;
//		Connection conection = JDBCutils.getConection();
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");
			Connection conection = ds.getConnection();
			preparedStatement = conection.prepareStatement(sql);
			preparedStatement.setInt(1, order_id);
			boolean execute = preparedStatement.execute();
			System.out.println(execute+"1123");
			System.out.println("刪除ID為:" + order_id);
			return execute;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		} finally {
//			JDBCutils.closeResource(conection, preparedStatement);
		}
	}


//更新訂單
	public boolean updateOrder(OrderBean order) {
		String sql = "UPDATE Orders SET customer_id = ?, caregiver_id = ?, order_date = ?, start_date = ?, end_date = ?, status = ?, total_price = ? "
				+ "WHERE order_id = ?";
//		Connection conection = JDBCutils.getConection();
		PreparedStatement preparedStatement = null;
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");
			Connection conection = ds.getConnection();
			preparedStatement = conection.prepareStatement(sql);
			preparedStatement.setInt(1, order.getCustomerId());
			preparedStatement.setInt(2, order.getCaregiverId());

			preparedStatement.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
			preparedStatement.setTimestamp(4, new Timestamp(order.getStartDate().getTime()));
			preparedStatement.setTimestamp(5, new Timestamp(order.getEndDate().getTime()));
			preparedStatement.setString(6, order.getStatus());
			preparedStatement.setInt(7, order.getTotalPrice());
			preparedStatement.setInt(8, order.getOrderId());
			preparedStatement.execute();
			System.out.println("更新商品為" + order.getOrderId());
			System.out.println(order.toString());
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
//			JDBCutils.closeResource(conection, preparedStatement);
		}
		return false;
	}

//根據顧客ID查詢
	public List<OrderBean> getOrderByCustomerId(int customerId) throws SQLException {
		String sql = "SELECT * FROM Orders WHERE customer_id = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		List<OrderBean> orders = new ArrayList<>(); // 用來儲存多筆訂單

		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");
			Connection conection = ds.getConnection();

			preparedStatement = conection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				int orderId = resultset.getInt("order_id");
				int caregiverId = resultset.getInt("caregiver_id");
				Date orderDate = resultset.getDate("order_Date");
				Date startDate = resultset.getDate("start_Date");
				Date endDate = resultset.getDate("end_Date");
				String status = resultset.getString("status");
				int totalPrice = resultset.getInt("total_price");

				// 創建 OrderBean 對象並加入 List
				OrderBean orderBean = new OrderBean(orderId, customerId, caregiverId, orderDate, startDate, endDate,
						status, totalPrice);
				orders.add(orderBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 請記得關閉資源
			if (preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (resultset != null)
				try {
					resultset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		// 返回訂單列表
		return orders;
	}

	
	
//查詢全部
	public List<OrderBean> getAllOrder() {
		String sql = "SELECT * FROM Orders ORDER BY order_id ASC";
		ArrayList<OrderBean> orders = new ArrayList<>();
		PreparedStatement prepardStatement = null;
		ResultSet resultset = null;
		Connection conection = null;

		try {
			// JNDI 查詢資料源
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");

			// 確保資料庫連線成功
			conection = ds.getConnection();
			System.out.println("Database connection established.");

			// 執行 SQL 查詢
			prepardStatement = conection.prepareStatement(sql);
			resultset = prepardStatement.executeQuery();

			// 處理查詢結果
			while (resultset.next()) {
				OrderBean order = new OrderBean();
				order.setOrderId(resultset.getInt("order_id"));
				order.setCustomerId(resultset.getInt("customer_id"));
				order.setCaregiverId(resultset.getInt("caregiver_id"));
				order.setOrderDate(resultset.getDate("order_Date"));
				order.setStartDate(resultset.getDate("start_Date"));
				order.setEndDate(resultset.getDate("end_Date"));
				order.setStatus(resultset.getString("status"));
				order.setTotalPrice(resultset.getInt("total_price"));

				orders.add(order);
			}

			if (orders.isEmpty()) {
				System.out.println("No orders found.");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // 打印SQL錯誤信息
		} catch (NamingException e) {
			e.printStackTrace(); // 打印資料源配置錯誤
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (prepardStatement != null)
					prepardStatement.close();
				if (conection != null)
					conection.close();
			} catch (SQLException e) {
				e.printStackTrace(); // 關閉資源時的錯誤處理
			}
		}

		return orders;
	}

	// 根據訂單狀態查詢
	public List<OrderBean> getOrderByStatus(String status) {
		ArrayList<OrderBean> orders = new ArrayList<>();
		String sql = "SELECT * FROM orders WHERE status = ?";
//		Connection conection = JDBCutils.getConection();
		PreparedStatement preparedStatement = null;
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");
			conection = ds.getConnection();
			preparedStatement = conection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			ResultSet resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				OrderBean order = new OrderBean();

				order.setOrderId(resultset.getInt("order_id"));
				order.setCustomerId(resultset.getInt("customer_id"));
				order.setCaregiverId(resultset.getInt("caregiver_id"));

				order.setOrderDate(resultset.getDate("order_Date"));
				order.setStartDate(resultset.getDate("start_Date"));
				order.setEndDate(resultset.getDate("end_Date"));

				// order.setStatus(resultset.getString("status"));
				order.setTotalPrice(resultset.getInt("total_price"));

				orders.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			JDBCutils.closeResource(conection, preparedStatement);
		}
		return orders;
	}

	// 根據看護ID搜尋
	public OrderBean getOrderByCaregiveId(int caregiverId) {
		String sql = "SELECT * FROM Orders WHERE customer_id = ?";
		PreparedStatement preparedStatement = null;
//		Connection conection = JDBCutils.getConection();
		ResultSet resultset = null;
		OrderBean OrderBean = null;
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");
			conection = ds.getConnection();

			preparedStatement = conection.prepareStatement(sql);
			preparedStatement.setInt(1, caregiverId);
			resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				int orderId = resultset.getInt("order_id");
				int customerId = resultset.getInt("customer_id");

				// int caregiverId = resultset.getInt("caregiver_id");

				Date orderDate = resultset.getDate("order_Date");
				Date startDate = resultset.getDate("start_Date");
				Date endDate = resultset.getDate("end_Date");

				String status = resultset.getString("status");
				int totalPrice = resultset.getInt("total_price");

				OrderBean = new OrderBean(orderId, customerId, caregiverId, orderDate, startDate, endDate, status,
						totalPrice);
				System.out.println(OrderBean.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			JDBCutils.closeResource(conection, preparedStatement, resultset);
		}
		// 返回查詢到的訂單列表
		return OrderBean;
	}

	//根據訂單編號查詢
	public OrderBean getOrderByOrderId(int orderId) {
		String sql = "SELECT * FROM Orders WHERE order_id = ?";
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		OrderBean OrderBean = null;

		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/testdb");
			connection = ds.getConnection(); // 使用DataSource取得連線

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderId); // 設定查詢條件
			resultSet = preparedStatement.executeQuery();

			// 如果查詢有結果，將結果封裝成OrderBean
			if (resultSet.next()) {
				int customerId = resultSet.getInt("customer_id");
				int caregiverId = resultSet.getInt("caregiver_id");

				Date orderDate = resultSet.getDate("order_date");
				Date startDate = resultSet.getDate("start_date");
				Date endDate = resultSet.getDate("end_date");

				String status = resultSet.getString("status");
				int totalPrice = resultSet.getInt("total_price");

				// 創建OrderBean物件並填充查詢結果
				OrderBean = new OrderBean(orderId, customerId, caregiverId, orderDate, startDate, endDate, status,
						totalPrice);
				System.out.println(OrderBean.toString()); // 可選的輸出，便於調試
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 關閉資源
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 返回查詢到的訂單
		return OrderBean;
	}

	// 根據日期查詢
	public List<OrderBean> getOrderByOrderDate(Date orderDate) {
		ArrayList<OrderBean> orders = new ArrayList<>();
		String sql = "SELECT * FROM orders WHERE CONVERT(DATE, order_date)= ?";
//		Connection conection = JDBCutils.getConection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conection.prepareStatement(sql);
			// 轉換 java.util.Date 為 java.sql.Date
			java.sql.Date sqlDate = new java.sql.Date(orderDate.getTime());
			// 設置查詢日期參數
			preparedStatement.setDate(1, sqlDate);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				OrderBean order = new OrderBean();

				order.setOrderId(resultset.getInt("order_id"));
				order.setCustomerId(resultset.getInt("customer_id"));
				order.setCaregiverId(resultset.getInt("caregiver_id"));

				order.setStartDate(resultset.getDate("start_Date"));
				order.setEndDate(resultset.getDate("end_Date"));

				order.setStatus(resultset.getString("status"));
				order.setTotalPrice(resultset.getInt("total_price"));

				orders.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			JDBCutils.closeResource(conection, preparedStatement);
		}
		return orders;
	}

}