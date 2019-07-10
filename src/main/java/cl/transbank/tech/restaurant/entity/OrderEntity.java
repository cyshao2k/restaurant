package cl.transbank.tech.restaurant.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;


	@JoinColumn(name = "table_id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private TableEntity table;
	
	@ManyToMany
    @JoinTable(name = "Orders_Dish",
        joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"))
	private Set<DishEntity> dishes;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TableEntity getTable() {
		return table;
	}

	public void setTable(TableEntity table) {
		this.table = table;
	}

	public Set<DishEntity> getDishes() {
		return dishes;
	}

	public void setDishes(Set<DishEntity> dishes) {
		this.dishes = dishes;
	}
}
