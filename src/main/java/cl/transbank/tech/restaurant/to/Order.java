package cl.transbank.tech.restaurant.to;

public class Order {

	private int id;
	private Table table;
	private Dish[] dishes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public Dish[] getDishes() {
		return dishes;
	}
	public void setDishes(Dish[] dishes) {
		this.dishes = dishes;
	}
}
