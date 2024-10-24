package site.hossainrion.Ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController
{
	@GetMapping("/report")
	public String report(Model model)
	{
		return "report";
	}

}