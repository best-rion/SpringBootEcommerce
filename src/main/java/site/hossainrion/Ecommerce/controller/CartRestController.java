package site.hossainrion.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.hossainrion.Ecommerce.service.CartService;


@RestController
public class CartRestController
{
	@Autowired
	CartService cartService;

	@PutMapping(value="/addToCart")
    String addToCart(@RequestBody String id)
	{
		int product_id = Integer.parseInt(id);
		
		String returnValue = cartService.add(product_id);
		
		return returnValue;
    }

	
	@PutMapping(value="/increaseQty")
    String increaseQty(@RequestBody String id)
	{
		int product_id = Integer.parseInt(id);
		
		String returnValue = cartService.increaseQuantity(product_id);
		
		return returnValue;
    }

	
	@PutMapping(value="/decreaseQty")
    String decreaseQty(@RequestBody String id)
	{
		int product_id = Integer.parseInt(id);
		
		String returnValue = cartService.decreaseQuantity(product_id);
		
		return returnValue;
    }
	
	
	@PutMapping(value="/removeItem")
    void removeItem(@RequestBody String id)
	{
		int product_id = Integer.parseInt(id);
		
		cartService.remove(product_id );
    }
	
}