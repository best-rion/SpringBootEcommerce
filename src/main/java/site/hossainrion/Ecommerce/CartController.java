package site.hossainrion.Ecommerce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import site.hossainrion.Ecommerce.home.ProductRepository;

@Controller
public class CartController
{
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/cart")
    public String home(Model model) throws IOException
	{
		List<Cart> cart_items = cartRepository.findByOwner(CurrentUser.id);
		
		List<Item> items = new ArrayList<Item>();
		
		for (Cart cart_item : cart_items)
		{
			Item item = new Item();
			item.product = productRepository.findById(cart_item.getProduct());
			item.quantity = cart_item.getQuantity();
			
			items.add(item);
		}
		
		model.addAttribute("items", items);
		return "cart";
    }
}