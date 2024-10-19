package site.hossainrion.Ecommerce;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int item_id;
	
	private int product;
	private int quantity;
	private int owner;
	
	public int getItem_id()
	{
		return item_id;
	}
	
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	
	public int getProduct()
	{
		return product;
	}
	
	public void setProduct(int product) {
		this.product = product;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getOwner()
	{
		return owner;
	}
	
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
}