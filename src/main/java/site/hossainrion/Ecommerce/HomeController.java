package site.hossainrion.Ecommerce;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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
}