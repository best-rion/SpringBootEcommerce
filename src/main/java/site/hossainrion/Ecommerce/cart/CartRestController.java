package site.hossainrion.Ecommerce.cart;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.hossainrion.Ecommerce.CurrentUser;


@RestController
public class CartRestController
{
	@Autowired
	CartRepository cartRepository;

	@PutMapping(value="/addToCart")
    void home(@RequestBody String id)
	{
		int product_id = Integer.parseInt(id);
		
		List<Cart> user_cart = cartRepository.findByOwnerRef(CurrentUser.id);
		
		boolean productAlreadyInCart = false;
		for ( Cart item : user_cart )
		{
			if (item.getProductRef() == product_id)
			{
				productAlreadyInCart = true;
			}
		}
		
		if (!productAlreadyInCart)
		{	
			Cart newCart = new Cart();
			newCart.setProductRef(product_id);
			newCart.setOwnerRef(CurrentUser.id);
			newCart.setQuantity(1);
			
			cartRepository.save(newCart);
			
			System.out.println("//Product inserted");
		}
		else
		{
			System.out.println("//Already in cart");
		}
		
    }
}