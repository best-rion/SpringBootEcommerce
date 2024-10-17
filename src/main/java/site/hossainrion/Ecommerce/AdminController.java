package site.hossainrion.Ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;

import site.hossainrion.Ecommerce.Database.DBInfo;

@Controller
public class AdminController
{
	@GetMapping("/admin")
	public String adminForm(Model model)
	{
		model.addAttribute("watch", new Product());
		return "admin";
	}
	
	@PostMapping("/admin")
	public String save(@ModelAttribute Product product, Model model)
    {                                           // ^------- This is what we get from submitted form

        try {



			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection(DBInfo.url, DBInfo.user, DBInfo.password);
			
			// 2. Create a statement
			PreparedStatement myStmt = myConn.prepareStatement("INSERT INTO "+DBInfo.table+" (brand, name, price, stock, pic_url) VALUES (?,?,?,?,?)");
			

			myStmt.setString(1, product.getBrand());
			myStmt.setString(2, product.getName());
			myStmt.setInt(3, product.getPrice());
			myStmt.setInt(4, product.getStock());
			myStmt.setString(5, product.getPic_url());
			
			// 3.Execute SQL query
			boolean error = myStmt.execute();
			
			if (!error)
            {
		        model.addAttribute("result", product);
				return "success";
            }
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        return null;
    }
}