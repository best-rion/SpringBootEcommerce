package site.hossainrion.Ecommerce.reg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import site.hossainrion.Ecommerce.CurrentUser;

@Controller
public class LogOut
{
	
	@GetMapping("/logout")
	public String logout()
	{
		CurrentUser.id = 0;
		return "redirect:/home";
	}
}