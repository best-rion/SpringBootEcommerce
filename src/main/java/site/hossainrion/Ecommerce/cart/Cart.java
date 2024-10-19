package site.hossainrion.Ecommerce.cart;

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
	private int id;
	
	private int productRef;
	private int quantity;
	private int ownerRef;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductRef() {
		return productRef;
	}

	public void setProductRef(int productRef) {
		this.productRef = productRef;
	}

	public int getOwnerRef() {
		return ownerRef;
	}

	public void setOwnerRef(int ownerRef) {
		this.ownerRef = ownerRef;
	}
	
}