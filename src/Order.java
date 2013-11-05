

import java.util.ArrayList;
import java.util.Date;

public class Order {

	private ArrayList<Item> items;
	private double orderTotal = 0.0;
	private int orderNumber;
	private int tableNumber;
	private long startTime, endTime, orderTime;
	private String totalOrderTime;
	private boolean isPaid=false;
	private int empID;
	private Date orderDate;
	
	public Order() {
	}

	public Order(ArrayList<Item> items, int table, int num) {
		setOrderNumber(num);
		setTableNumber(table);
		this.items = items;
		orderTotal = total(items); // calculate order total
		//Consider turning this into a a date and time instead of integer in milliseconds
		orderDate = new Date(); // Gets current time in date format instead of integer format
		startTime = System.currentTimeMillis(); // get the system time at start
												// of order
	}
	/**
	 * This class is for creating orders that already exist in the database
	 * @param items
	 * @param table
	 * @param orderNum
	 * @param empId
	 * @param orderDate
	 */
	public Order(ArrayList<Item> items, int table, int orderNum, int empId, Date orderDate){
		
		setOrderNumber(orderNum);
		setTableNumber(table);
		this.items = items;
		this.setEmpID(empId);
		this.orderDate = orderDate;
		orderTotal = total(items);

	}

	// work on this later
	public String toString() {
		String ret = "Order number: " + orderNumber + " Table: " + tableNumber
				+ "\n";

		for (Item i : items) {
			ret += "\n" + i.toString();
		}

		return ret;
	}

	public String endOrder() {
		endTime = System.currentTimeMillis();
		orderTime = endTime - startTime;
		orderTime /= 1000; // get time into seconds rather than millis
		int hours = 0, min = 0, secs = 0;

		// do {
		if (orderTime < 60) { // if order under one min
			secs = (int) orderTime;
		} else if (orderTime > 59 && orderTime < 3600) { // if order under one
															// hour but over one
															// min
			min = (int) orderTime / 60;
			secs = (int) orderTime % 60;
		} else {
			hours = (int) orderTime / 3600;
			int temp = (int) orderTime % 3600;
			min = temp / 60;
			secs = temp % 60;
		}
		// } while (orderTime < 60);
		totalOrderTime = "Total order time was: " + hours + " hours and " + min
				+ " minutes and " + secs + " seconds.";
		return totalOrderTime;
	}

	private double total(ArrayList<Item> items) {
		double total = 0;
		for (Item i : items) {
			total += i.getItemPrice();
		}
		return total;
	}

	public void applyDiscount(double percent) { // apply a discount in percent
												// to total
		orderTotal = orderTotal * ((100 - percent)/100);
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public String getTotalOrderTime() {
		return totalOrderTime;
	}

	public void setTotalOrderTime(String totalOrderTime) {
		this.totalOrderTime = totalOrderTime;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

}
