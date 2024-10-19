package site.hossainrion.Ecommerce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import site.hossainrion.Ecommerce.home.ProductRepository;

@RestController
public class CartRestController
{
	@Autowired
	CartRepository cartRepository;

	@PostMapping("/cart")
    public String home(Model model) throws IOException
	{
		CartRepository cartRepository;
		
		
    }
}