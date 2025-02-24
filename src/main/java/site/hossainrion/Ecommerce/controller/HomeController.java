package site.hossainrion.Ecommerce.controller;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import site.hossainrion.Ecommerce.model.Cart;
import site.hossainrion.Ecommerce.model.Product;
import site.hossainrion.Ecommerce.model.User;
import site.hossainrion.Ecommerce.repository.CartRepository;
import site.hossainrion.Ecommerce.repository.ProductRepository;
import site.hossainrion.Ecommerce.service.MyUserDetailsService;


@Controller
public class HomeController
{
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	
	@GetMapping("/")
    public String defaultPage()
	{
		return  "redirect:/ecommerce/home/page-1";
    }
	
	
	@GetMapping("/home/page-{page}")
    public String home(@PathVariable int page, Model model, @AuthenticationPrincipal User principal) throws IOException
	{
		if (principal != null)
		{
			return String.format("redirect:/ecommerce/home/page-%d/user-%d", page, principal.getID());
		}
		else
		{
			Page<Product> productPage = productRepository.findAll( PageRequest.of(page-1, 8) );

			int totalPages = productPage.getTotalPages();
			if (totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
			}

			model.addAttribute("productPage", productPage );

			return  "home";
		}
    }
	
	
	@GetMapping("/home/page-{page}/user-{id}")
    public String homeUser(@PathVariable int page, @PathVariable int id, Model model) throws IOException
	{
		User principal = userDetailsService.getPrincipal();

		if (id == principal.getID())
		{
			Page<Product> productPage = productRepository.findAll( PageRequest.of(page-1, 8) );

			List<Cart> cart_items  = cartRepository.findByOwnerId(id);


			int totalQty = 0;
			for (Cart cart_item : cart_items )
			{
				totalQty += cart_item.getQuantity();
			}


			int totalPages = productPage.getTotalPages();
			if (totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
			}


			model.addAttribute("totalQty", totalQty);
			model.addAttribute("productPage", productPage);
			return "home";
		}
		else
		{
			return String.format("redirect:/ecommerce/home/page-%d/user-%d", page, principal.getID());
		}
    }
	
	
	@GetMapping("/about")
    public String about(Model model)
	{
		return "about";
    }
}