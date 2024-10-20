package site.hossainrion.Ecommerce.cart;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfCreator
{
	public static void create(List<Item> items) throws DocumentException, URISyntaxException, IOException
	{

		
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("receipt.pdf"));

		document.open();

		PdfPTable table = new PdfPTable(5);
		addTableHeader(table);
		
		// My Code
		for (Item item : items)
		{
			imageDownloader(item.product.getPic_url(),String.format("%d",item.product.getId()));
			
			addImageCell(table, "/home/hossain/Downloads/Images/"+String.format("%d", item.product.getId())+".jpg" );
			addRows(table, item.product.getBrand());
			addRows(table, item.product.getName());
			addRows(table, String.format("%d", item.quantity) );
			addRows(table, String.format("%d", item.product.getPrice() * item.quantity) );
		}

		document.add(table);
		document.close();
	}
	
	private static void addTableHeader(PdfPTable table) {
	    Stream.of("Image", "Brand", "Model", "Quantity", "Price")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	
	private static void addRows(PdfPTable table, String cell) {
	    table.addCell(cell);
	}
	
	
	private static void addCustomRows(PdfPTable table) 
		  throws URISyntaxException, BadElementException, IOException 
	{
		addImageCell(table, "orange.jpg");

	    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
	    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(horizontalAlignCell);
	
	    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
	    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
	    table.addCell(verticalAlignCell);
	    
	    verticalAlignCell = new PdfPCell(new Phrase("Empty"));
	    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
	    table.addCell(verticalAlignCell);
	}
	
	private static void addImageCell(PdfPTable table, String fileName) throws URISyntaxException, BadElementException, MalformedURLException, IOException
	{
		//Path path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
	    //Image img = Image.getInstance(path.toAbsolutePath().toString());
		
		Image img = Image.getInstance(fileName);
	    img.scalePercent(20);

	    PdfPCell imageCell = new PdfPCell(img);
	    table.addCell(imageCell);
	}
	
	private static void imageDownloader(String link, String name) throws IOException
	{
		URL url = new URL(link);
		BufferedImage img = ImageIO.read(url);
		File file = new File("/home/hossain/Downloads/Images/"+name+".jpg");
		ImageIO.write(img, "jpg", file);
	}
}