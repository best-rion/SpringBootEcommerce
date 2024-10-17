package site.hossainrion.Ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class AdminController
{
	@Autowired 
	private UserRepository userRepository;
	
	
	
	@GetMapping("/admin")
	public String adminForm(Model model)
	{
		model.addAttribute("watch", new Product());
		return "admin";
	}
	
	@PostMapping("/admin")
	public String save(@ModelAttribute Product product, Model model)
    {		                                 // ^------- This is what we get from submitted form
		
		userRepository.save(product);
		model.addAttribute("result", product);
		return "success";

    }
}