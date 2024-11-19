package site.hossainrion.Ecommerce.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.hossainrion.Ecommerce.model.Cart;
import site.hossainrion.Ecommerce.model.Product;
import site.hossainrion.Ecommerce.model.User;
import site.hossainrion.Ecommerce.repository.CartRepository;
import site.hossainrion.Ecommerce.repository.ProductRepository;
import site.hossainrion.Ecommerce.repository.UserRepository;


@RestController
public class CartRestController
{
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@PutMapping(value="/addToCart")
    String addToCart(@RequestBody String id)
	{

		int product_id = Integer.parseInt(id);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username =  auth.getName();
		User principal = userRepository.findByUsername(username);
		

		System.out.println(principal);
		
		
		if (principal != null)
		{
			List<Cart> user_cart = cartRepository.findByOwnerRef(principal.getID());
			
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
				newCart.setOwnerRef(principal.getID());
				newCart.setQuantity(1);
				
				cartRepository.save(newCart);
				
				return "1";
			}
		}
		return "0";
    }
	
	
	
	@PutMapping(value="/increaseQty")
    String increaseQty(@RequestBody String id)
	{
		int product_id = Integer.parseInt(id);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username =  auth.getName();
		User principal = userRepository.findByUsername(username);
		
		List<Cart> cart_items= cartRepository.findByOwnerRef(principal.getID());
		
		for ( Cart cart_item : cart_items )
		{
			if (cart_item.getProductRef() == product_id)
			{
				
				Product product = productRepository.findById(product_id);
				
				Cart desired_item = cart_item;
				
				if ( product.getStock() > desired_item.getQuantity() )
				{
					desired_item.setQuantity(desired_item.getQuantity()+1);
					cartRepository.save(desired_item);

					return "1";
				}
			}
		}
		return "0";
    }
	
	
	
	@PutMapping(value="/decreaseQty")
    String decreaseQty(@RequestBody String id)
	{
		int product_id = Integer.parseInt(id);
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username =  auth.getName();
		User principal = userRepository.findByUsername(username);
		
		List<Cart> cart_items= cartRepository.findByOwnerRef(principal.getID());
		

		for ( Cart cart_item : cart_items )
		{
			if (cart_item.getProductRef() == product_id)
			{
				
				Cart desired_item = cart_item;
				
				if ( desired_item.getQuantity() > 1 )
				{
					desired_item.setQuantity(desired_item.getQuantity()-1);
					cartRepository.save(desired_item);

					return "1";
				}
			}
		}
		return "0";
    }
	
	@PutMapping(value="/removeItem")
    void removeItem(@RequestBody String id)
	{
		int product_id = Integer.parseInt(id);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username =  auth.getName();
		User principal = userRepository.findByUsername(username);
		
		cartRepository.deleteByProductRefAndOwnerRef(product_id, principal.getID());
    }
	
}