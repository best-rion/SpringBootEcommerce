package myPackage;

public class Product
{
	private int product_id;
	
	private String brand;
	public String name;
	public int price;
	public int stock;
	public String pic_url;
	
	public int getProduct_id()
	{
		return product_id;
	}
	
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public String getBrand()
	{
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getStock()
	{
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getPic_url()
	{
		return pic_url;
	}
	
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
}