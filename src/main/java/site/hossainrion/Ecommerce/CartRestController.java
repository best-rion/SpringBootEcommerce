package site.hossainrion.Ecommerce;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CartRestController
{
	@Autowired
	CartRepository cartRepository;

	@PutMapping(value="/cartService")
    void home(@RequestBody String payload)
	{
		System.out.println(payload);
		
    }
}