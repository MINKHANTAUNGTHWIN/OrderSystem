package com.res.order.dao;

public class Order {
	private int orderId;
	private int orderquantity;
	private int orderStatus;
	private String orderItemName;
	private int tableId;
	private int customerId;
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	private int orderPrice;
	private int pageId;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderquantity() {
		return orderquantity;
	}
	public void setOrderquantity(int orderquantity) {
		this.orderquantity = orderquantity;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderItemName() {
		return orderItemName;
	}
	public void setOrderItemName(String orderItemName) {
		this.orderItemName = orderItemName;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

}
