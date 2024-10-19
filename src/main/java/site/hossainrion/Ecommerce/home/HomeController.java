package site.hossainrion.Ecommerce.home;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import site.hossainrion.Ecommerce.CurrentUser;


@Controller
public class HomeController
{
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/home")
    public String home(Model model) throws IOException
	{
		List<Product> products = (List<Product>) productRepository.findAll();		
		
		model.addAttribute("watches", products);
		return "home";
    }
	
	@GetMapping("/home/user-{id}")
    public String homeUser(@PathVariable int id, Model model)
	{
		if (id == CurrentUser.id)
		{
			List<Product> products = (List<Product>) productRepository.findAll();		
			
			model.addAttribute("watches", products);
			return "home";
		}
		else
		{
			return (CurrentUser.id == 0) ? "redirect:/home" : String.format("redirect:/home/user-%d", CurrentUser.id);
		}
    }
	
	@GetMapping("/about")
    public String about()
	{
		return "about";
    }
}