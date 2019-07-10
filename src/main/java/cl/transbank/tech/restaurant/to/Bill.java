package cl.transbank.tech.restaurant.to;

import java.util.Date;
import java.util.List;

public class Bill {

	private int id;
	private Date billDate;
	private List<BillItem> items;
	private Table table;
	private int totalCost;
	private int taxes;
	private int tipping;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public List<BillItem> getItems() {
		return items;
	}
	public void setItems(List<BillItem> items) {
		this.items = items;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	public int getTaxes() {
		return taxes;
	}
	public void setTaxes(int taxes) {
		this.taxes = taxes;
	}
	public int getTipping() {
		return tipping;
	}
	public void setTipping(int tipping) {
		this.tipping = tipping;
	}
}
