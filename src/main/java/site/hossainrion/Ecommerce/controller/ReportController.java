package site.hossainrion.Ecommerce.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import site.hossainrion.Ecommerce.model.Cart;
import site.hossainrion.Ecommerce.model.Product;
import site.hossainrion.Ecommerce.repository.CartRepository;
import site.hossainrion.Ecommerce.repository.ProductRepository;
import site.hossainrion.Ecommerce.repository.UserRepository;

@Controller
public class ReportController
{
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@GetMapping("/report")
	public void report(Model model, HttpServletResponse response) throws IOException
	{

		// Export Cart table as EXCEL FILE
		Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Persons");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Cart ID");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Customer");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Product");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Quantity");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Price");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(5);
        headerCell.setCellValue("Date");
        headerCell.setCellStyle(headerStyle);





        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        int row_num = 1;
        
        List<Cart> carts = cartRepo.findAll();

        for (Cart cart: carts)
        {
            Row row = sheet.createRow( row_num );
            
            Product product = cart.getProduct();

            Cell cell = row.createCell(0);
            cell.setCellValue( cart.getId() );
            cell.setCellStyle(style);
    
            cell = row.createCell(1);
            cell.setCellValue( cart.getOwner().getUsername() );
            cell.setCellStyle(style);
    
            cell = row.createCell(2);
            cell.setCellValue( product.getBrand() );
            cell.setCellStyle(style);
    
            cell = row.createCell(3);
            cell.setCellValue( cart.getQuantity() );
            cell.setCellStyle(style);
    
            cell = row.createCell(4);
            cell.setCellValue( product.getPrice() );
            cell.setCellStyle(style);
    
            cell = row.createCell(5);
            cell.setCellValue( cart.getSoldDate().toString() );
            cell.setCellStyle(style);

            row_num++;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        
        ByteArrayInputStream is = new ByteArrayInputStream(outputStream.toByteArray());
        
        
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setContentLengthLong(outputStream.size());
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
									                    .filename("report.xlsx", StandardCharsets.UTF_8)
									                    .build()
									                    .toString());
		is.transferTo(response.getOutputStream());
        
	}

}