package site.hossainrion.Ecommerce.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import site.hossainrion.Ecommerce.model.Cart;
import site.hossainrion.Ecommerce.model.Product;
import site.hossainrion.Ecommerce.model.User;
import site.hossainrion.Ecommerce.repository.CartRepository;
import site.hossainrion.Ecommerce.repository.ProductRepository;
import site.hossainrion.Ecommerce.repository.UserRepository;


@Controller
public class HomeController
{
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/")
    public String defaultPage()
	{
		return  "redirect:/home";
    }
	
	@GetMapping("/home")
    public String home(Model model) throws IOException
	{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username =  auth.getName();
		User principal = userRepository.findByUsername(username);
		
		
		if (principal != null)
		{
			return String.format("redirect:/home/user-%d", principal.getID());
		}
		else
		{
			List<Product> products = (List<Product>) productRepository.findAll();		
			
			model.addAttribute("watches", products);
			model.addAttribute("notLoggedIn", 0);
			
			return  "home";
		}
    }
	
	@GetMapping("/home/user-{id}")
    public String homeUser(@PathVariable int id, Model model) throws IOException
	{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username =  auth.getName();
		User principal = userRepository.findByUsername(username);
		
		if (id == principal.getID())
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
			return String.format("redirect:/home/user-%d", principal.getID());
		}
    }
	
	@GetMapping("/about")
    public String about(Model model)
	{
		model.addAttribute("notLoggedIn", 0);
		return "about";
    }
}