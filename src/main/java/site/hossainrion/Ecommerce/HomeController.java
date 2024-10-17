package site.hossainrion.Ecommerce;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController
{
	@GetMapping("/home")
    public String home(Model model) throws IOException
	{
		String jsonContent = Json.jsonToString("watches.json");
		Product[] products = Json.getProducts(jsonContent);
		
		
		model.addAttribute("watches", products);
		return "home2";
    }
}