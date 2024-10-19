package site.hossainrion.Ecommerce.reg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUp
{
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/signup")
	public String signupGet(Model model)
	{
		model.addAttribute("userInfo", new User());
		
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signupPost(@ModelAttribute User user,Model model)
	{
		userRepo.save(user);
		
		return "redirect:login";
	}
}