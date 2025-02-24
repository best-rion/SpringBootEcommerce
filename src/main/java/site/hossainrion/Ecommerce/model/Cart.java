package site.hossainrion.Ecommerce.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Product product;
	private int quantity;
	@ManyToOne
	private User owner;
	private int sold = 0;
	
	@Temporal(TemporalType.DATE)
    private Date soldDate;

	public Cart(){
		this.soldDate = new Date();
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public Date getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}
}