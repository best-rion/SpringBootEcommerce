package site.hossainrion.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import site.hossainrion.Ecommerce.DTO.UserDTO;
import site.hossainrion.Ecommerce.model.User;
import site.hossainrion.Ecommerce.repository.UserRepository;

@Controller
public class SignUp
{
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/signup")
	public String signupGet(Model model)
	{
		model.addAttribute("userInfo", new UserDTO());
		
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signupPost(@ModelAttribute UserDTO user, Model model)
	{
    	if ( user.getPassword().equals(user.getConfirm_password()))
    	{
    		User newUser = new User();
    		newUser.setUsername(user.getUsername());
    		newUser.setPassword(user.getPassword());
    		
    		userRepo.save(newUser);
    		return "redirect:/login";
    	}
    	else
    	{
    		return "redirect:/signup";
    	}
	}
}