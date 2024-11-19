package site.hossainrion.Ecommerce.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import site.hossainrion.Ecommerce.DTO.CartDTO;


public class PdfCreator
{
	
	public static ByteArrayOutputStream create(List<CartDTO> items, String username) throws DocumentException, URISyntaxException, IOException
	{

		Document document = new Document();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

		document.open();
		
		// Header
		Paragraph header = new Paragraph("WATCH SHOP", new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD));
		header.setAlignment(Element.ALIGN_CENTER);
		header.setSpacingAfter(12.0f);
		
		document.add(header);
		
		// UserInfo
		String name = String.format("Customer   :    %s", username);
		Paragraph customerName = new Paragraph(name, new Font(Font.FontFamily.HELVETICA));
		customerName.setSpacingAfter(20.0f);
		customerName.setIndentationLeft(52.0f);
		
		document.add(customerName);
		
		// Table
		PdfPTable table = new PdfPTable(5);
		addTableHeader(table);
		
		// My Code
		int totalPrice = 0;
		
		for (CartDTO item : items)
		{
			Image image = linkToImage(item.product.getPic_url());
			
			addImageToCell(table, image );
			addRows(table, item.product.getBrand());
			addRows(table, item.product.getName());
			addRows(table, String.format("%d", item.quantity) );
			

	        PdfPCell cell = new PdfPCell();
	        cell.setPhrase(new Phrase( String.format("%d", item.product.getPrice() * item.quantity)));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    table.addCell(cell);
			
			totalPrice += item.product.getPrice() * item.quantity;
		}
		
		addTranparentRows(table, "");
		addTranparentRows(table, "");
		addTranparentRows(table, "");
		addTranparentRows(table, "Total Price:");
		addTranparentRows(table, String.format("%d", totalPrice));
		
		addTranparentRows(table, "");
		addTranparentRows(table, "");
		addTranparentRows(table, "");
		addTranparentRows(table, "Shipping Fee:");
		addTranparentRows(table, "60");
		
		
		addTranparentRows(table, "");
		addTranparentRows(table, "");
		addTranparentRows(table, "");
		PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase("Total Amount:"));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorderWidthLeft(0.0f);
		cell.setBorderWidthRight(0.0f);
		cell.setBorderWidthBottom(0.0f);
	    table.addCell(cell);
	    
	    
	    cell = new PdfPCell();
        cell.setPhrase(new Phrase(String.format("%d", totalPrice+60)));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorderWidthLeft(0.0f);
		cell.setBorderWidthRight(0.0f);
		cell.setBorderWidthBottom(0.0f);
	    table.addCell(cell);
		
		document.add(table);
		
		document.close();
		
		return outputStream;
	}
	
	
	
	
	private static void addTableHeader(PdfPTable table) 
	{
	    Stream.of("Image", "Brand", "Model", "Quantity", "Price")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        header.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(header);
	    });
	}
	
	
	private static void addRows(PdfPTable table, String cell) 
	{
	    table.addCell(cell);
	}
	
	private static void addTranparentRows(PdfPTable table, String content) 
	{
		PdfPCell cell = new PdfPCell();
		cell.setBorderWidth(0);
		cell.setPhrase(new Phrase(content));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
	    table.addCell(cell);
	}
	
	private static void addImageToCell(PdfPTable table, Image img) throws URISyntaxException, BadElementException, MalformedURLException, IOException
	{
	    img.scalePercent(20);

	    PdfPCell imageCell = new PdfPCell(img);
	    imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    imageCell.setPadding(5.0f);
	    table.addCell(imageCell);
	}
	

	private static Image linkToImage(String link) throws IOException, BadElementException
	{
		URL url = new URL(link);
		BufferedImage img = ImageIO.read(url);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "jpg", baos);
		Image iTextImage = Image.getInstance(baos.toByteArray());
		
		return iTextImage;
	}
}