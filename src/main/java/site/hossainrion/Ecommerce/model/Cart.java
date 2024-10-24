package site.hossainrion.Ecommerce.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
	private boolean sold = false;
	
	@Temporal(TemporalType.DATE)
    private Date soldDate;
	
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

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public Date getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

	
}