package site.hossainrion.Ecommerce;


import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController
{
	public class Product
	{
		public String name;
		public int price;
		
		public Product(String name, int price)
		{
			this.name = name;
			this.price = price;
		}
	}
	
	@GetMapping("/home")
    public String home(Model model)
	{
		ArrayList<Product> products = new ArrayList<Product>();
		products.add( new Product("apple",25));
		products.add( new Product("orange",35));
		
		
		model.addAttribute("fruits", products);
		return "home";
    }
}