package site.hossainrion.Ecommerce.reg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import site.hossainrion.Ecommerce.CurrentUser;

@Controller
public class LogIn
{
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/login")
	public String loginGet(Model model)
	{
		model.addAttribute("userInfo", new FormUser());
		
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(@ModelAttribute FormUser formUser)
	{
		User dbUser = userRepo.findByUsername(formUser.getUsername());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if ( encoder.matches(formUser.getPassword(), dbUser.getPassword()) )
		{
			CurrentUser.id = dbUser.getID();
			System.out.println("Success");
		}
		else
		{
			System.out.println("Failure");
		}
		
		return String.format("redirect:/home/user-%d", CurrentUser.id);
	}
}