package site.hossainrion.Ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.hossainrion.Ecommerce.model.Cart;
import site.hossainrion.Ecommerce.model.Product;
import site.hossainrion.Ecommerce.model.User;
import site.hossainrion.Ecommerce.repository.CartRepository;
import site.hossainrion.Ecommerce.repository.ProductRepository;

@Service
public class CartService
{	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	public String add(int product_id, User principal)
	{
		if (principal != null)
		{
			List<Cart> user_cart = cartRepository.findByOwnerId(principal.getID());
			
			boolean productAlreadyInCart = false;
			
			for ( Cart item : user_cart )
			{
                if (item.getProduct().getId() == product_id) {
                    productAlreadyInCart = true;
                    break;
                }
			}
			
			if (!productAlreadyInCart)
			{	
				Cart newCart = new Cart();
				newCart.setProduct( productRepository.findById(product_id) );
				newCart.setOwner( principal );
				newCart.setQuantity(1);
				
				cartRepository.save(newCart);
				
				return "1";
			}
		}
		return "0";
	}
	
	
	
	public void remove(int product_id)
	{
		User principal = userDetailsService.getPrincipal();
		
		cartRepository.deleteByProductAndOwner(product_id, principal.getID());
	}
	
	
	
	public String increaseQuantity(int product_id)
	{
		User principal = userDetailsService.getPrincipal();
		
		List<Cart> cart_items= cartRepository.findByOwnerId(principal.getID());
		
		for ( Cart cart_item : cart_items )
		{
			if (cart_item.getProduct().getId() == product_id)
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
	
	
	
	public String decreaseQuantity(int product_id)
	{
		User principal = userDetailsService.getPrincipal();
		
		List<Cart> cart_items= cartRepository.findByOwnerId(principal.getID());
		
		for ( Cart cart_item : cart_items )
		{
			if (cart_item.getProduct().getId() == product_id)
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
}




