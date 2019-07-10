package cl.transbank.tech.restaurant.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Bill")
public class BillEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="bill_date", nullable=false)
	private Date billDate;
	
	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BillItemEntity> items;

	@JoinColumn(name = "table_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private TableEntity table;
	
	@Column(name="total_cost", nullable=false)
	private int totalCost;
	
	@Column(name="taxes", nullable=false)
	private int taxes;

	@Column(name="tipping", nullable=false)
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

	public TableEntity getTable() {
		return table;
	}

	public void setTable(TableEntity table) {
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

	public Set<BillItemEntity> getItems() {
		return items;
	}

	public void setItems(Set<BillItemEntity> items) {
		this.items = items;
	}
}
