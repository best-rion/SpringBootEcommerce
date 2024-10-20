package site.hossainrion.Ecommerce.cart;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.hossainrion.Ecommerce.CurrentUser;
import site.hossainrion.Ecommerce.home.Product;
import site.hossainrion.Ecommerce.home.ProductRepository;


@RestController
public class CartRestController
{
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;

	@PutMapping(value="/addToCart")
    String addToCart(@RequestBody String id)
	{
		if (CurrentUser.id != 0)
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
				
				return "1";
			}
		}
		return "0";
    }
	
	
	
	@PutMapping(value="/increaseQty")
    String increaseQty(@RequestBody String id)
	{
		int product_id = Integer.parseInt(id);
		
		List<Cart> cart_items= cartRepository.findByOwnerRef(CurrentUser.id);
		
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
		
		List<Cart> cart_items= cartRepository.findByOwnerRef(CurrentUser.id);
		

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
		
		cartRepository.deleteByProductRefAndOwnerRef(product_id, CurrentUser.id);
		
    }
}