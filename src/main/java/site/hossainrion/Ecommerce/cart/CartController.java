package site.hossainrion.Ecommerce.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import site.hossainrion.Ecommerce.CurrentUser;
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
		List<Cart> cart_items = cartRepository.findByOwnerRef(CurrentUser.id);
		
		List<Item> items = new ArrayList<Item>();
		
		int totalQty = 0;
		int totalPrice = 0;
		for (Cart cart_item : cart_items)
		{
			Item item = new Item();
			item.product = productRepository.findById(cart_item.getProductRef());
			item.quantity = cart_item.getQuantity();
			
			items.add(item);
			
			totalQty += cart_item.getQuantity();
			totalPrice += item.product.getPrice() * cart_item.getQuantity();
		}
		
		model.addAttribute("totalQty", totalQty);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("items", items);
		model.addAttribute("notLoggedIn", (CurrentUser.id == 0));
		return "cart";
    }
	
	
	
}