package OrderBean;

import java.sql.Timestamp;
import java.util.Date;

public class OrderBean {

	private int orderId;
	private int customerId;
	private int caregiverId;
	
	private Date orderDate;
	private Date startDate;
	private Date endDate;
	private String status;
	private int totalPrice;

	public OrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderBean(int orderId, int customerId, int caregiverId, Date orderDate, Date startDate,
			Date endDate, String status, int totalPrice) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.caregiverId = caregiverId;
		
		this.orderDate = orderDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.totalPrice = (int) totalPrice;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCaregiverId() {
		return caregiverId;
	}

	public void setCaregiverId(int caregiverId) {
		this.caregiverId = caregiverId;
	}

	

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderBean [orderId=" + orderId + ", customerId=" + customerId + ", caregiverId=" + caregiverId
				+ ", orderDate=" + orderDate + ", startDate=" + startDate + ", endDate=" + endDate + ", status="
				+ status + ", totalPrice=" + totalPrice + "]";
	}
	
}
