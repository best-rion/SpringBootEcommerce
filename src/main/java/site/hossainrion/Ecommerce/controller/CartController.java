package site.hossainrion.Ecommerce.controller;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itextpdf.text.DocumentException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import site.hossainrion.Ecommerce.DTO.CartDTO;
import site.hossainrion.Ecommerce.model.Cart;
import site.hossainrion.Ecommerce.model.User;
import site.hossainrion.Ecommerce.repository.ProductRepository;
import site.hossainrion.Ecommerce.repository.UserRepository;
import site.hossainrion.Ecommerce.util.CurrentUser;
import site.hossainrion.Ecommerce.util.PdfCreator;
import site.hossainrion.Ecommerce.repository.CartRepository;

@Controller
public class CartController
{
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;
	

	@Autowired
	UserRepository userRepository;


	@GetMapping("/cart")
    public String cart(Model model) throws IOException
	{
		List<Cart> cart_items = cartRepository.findByOwnerRef(CurrentUser.id);
		
		List<CartDTO> items = new ArrayList<CartDTO>();
		
		int totalQty = 0;
		int totalPrice = 0;
		for (Cart cart_item : cart_items)
		{
			CartDTO item = new CartDTO();
			item.product = productRepository.findById(cart_item.getProductRef());
			item.quantity = cart_item.getQuantity();
			items.add(item);
			
			totalQty += cart_item.getQuantity();
			totalPrice += item.product.getPrice() * cart_item.getQuantity();
		}
		
		model.addAttribute("totalQty", totalQty);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("items", items);
		model.addAttribute("notLoggedIn", (CurrentUser.id == 0));
		return "cart";
    }
	
	@GetMapping("/printCart")
    public String printCart(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		List<Cart> cart_items = cartRepository.findByOwnerRef(CurrentUser.id);
		
		List<CartDTO> items = new ArrayList<CartDTO>();
		for (Cart cart_item : cart_items)
		{
			CartDTO item = new CartDTO();
			item.product = productRepository.findById(cart_item.getProductRef());
			item.quantity = cart_item.getQuantity();
			
			items.add(item);
			
			
			cart_item.setSold(true);
			cart_item.setSoldDate(new Date());
			cartRepository.save(cart_item);
		}
		
		try
		{
			User theUser = userRepository.findById(CurrentUser.id);

			ByteArrayOutputStream document = PdfCreator.create(items, theUser.getUsername());
			ByteArrayInputStream is = new ByteArrayInputStream(document.toByteArray());
			
			response.setContentType("application/pdf");
			response.setContentLengthLong(document.size());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
										                    .filename("receipt.pdf", StandardCharsets.UTF_8)
										                    .build()
										                    .toString());

			is.transferTo(response.getOutputStream());


		}
		catch(FileNotFoundException | DocumentException | URISyntaxException  ex)
		{
			ex.printStackTrace();
		}
		
		return "redirect:/cart";
		//
    }
	
}