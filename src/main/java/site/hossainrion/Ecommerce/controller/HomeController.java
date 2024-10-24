package site.hossainrion.Ecommerce.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import site.hossainrion.Ecommerce.util.CurrentUser;
import site.hossainrion.Ecommerce.model.Cart;
import site.hossainrion.Ecommerce.model.Product;
import site.hossainrion.Ecommerce.repository.CartRepository;
import site.hossainrion.Ecommerce.repository.ProductRepository;


@Controller
public class HomeController
{
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartRepository cartRepository;

	@GetMapping("/home")
    public String home(Model model) throws IOException
	{
		List<Product> products = (List<Product>) productRepository.findAll();		
		
		model.addAttribute("watches", products);
		model.addAttribute("notLoggedIn", (CurrentUser.id == 0));
		
		return (CurrentUser.id == 0) ? "home" : String.format("redirect:/home/user-%d", CurrentUser.id);
    }
	
	@GetMapping("/home/user-{id}")
    public String homeUser(@PathVariable int id, Model model) throws IOException
	{
		if (id == CurrentUser.id)
		{
			List<Product> products = (List<Product>) productRepository.findAll();
			
			List<Cart> cart_items = cartRepository.findByOwnerRef(id);
			
			
			int totalQty = 0;
			for (Cart cart_item : cart_items )
			{
				totalQty += cart_item.getQuantity();
			}
			
			model.addAttribute("totalQty", totalQty);
			model.addAttribute("watches", products);
			return "home";
		}
		else
		{
			return (CurrentUser.id == 0) ? "redirect:/home" : String.format("redirect:/home/user-%d", CurrentUser.id);
		}
    }
	
	@GetMapping("/about")
    public String about(Model model)
	{
		model.addAttribute("notLoggedIn", (CurrentUser.id == 0));
		return "about";
    }
}