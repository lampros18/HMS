package gr.hua.dit.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.pdfbox.pdmodel.PDPage;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class PDFMaker {
	
	Document document;

	public void init() throws MalformedURLException, IOException, URISyntaxException, DocumentException {
		document = new Document(PageSize.A4);
		document.setMargins(0, 0, document.topMargin(), document.bottomMargin());
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream("housingApplication.pdf"));
		} catch (FileNotFoundException | DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		document.open();
		
		Path path = Paths.get(this.getClass().getResource("hua_logo.png").toURI());
	    Image img = Image.getInstance(path.toAbsolutePath().toString());
//		img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
		float x = (PageSize.A4.getWidth() - img.getScaledWidth()) / 2;
		float y = (PageSize.A4.getHeight() - img.getScaledHeight()) / 2;
		img.setAbsolutePosition(x, y);
		document.add(img);
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(90);
		
		
		addTableHeader(table);
		addRows(table);
//		try {
//			addCustomRows(table);
//		} catch (BadElementException | URISyntaxException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 
		try {
			document.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     path = Paths.get(this.getClass().getResource("er.png").toURI());
	     img = Image.getInstance(path.toAbsolutePath().toString());
//	    img.scalePercent(35);
	    float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
	               - document.rightMargin()) / img.getWidth()) * 100;

	    img.scalePercent(scaler);
	    document.newPage();
	    document.add(img);
		document.close();
	}
	
	private void addTableHeader(PdfPTable table) {
	    Stream.of("e-mail", "Personal Income", "Unemployed Parents", "Family Income", "Number of Studying Siblings", "Is Local Resident", "Creation Datetime")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	private void addRows(PdfPTable table) {
		table.addCell("it21625");
		table.addCell("0");
	    table.addCell("1");
	    table.addCell("1000");
	    table.addCell("0");
	    table.addCell("Yes");
	    table.addCell("2019-01-08 22:52:59");
	}
	
	private void addCustomRows(PdfPTable table) 
			  throws URISyntaxException, BadElementException, IOException {
//		ClassLoader.getSystemResource("resources/er.png").toURI()

			   
			 
//			    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
//			    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			    table.addCell(horizontalAlignCell);
//			 
//			    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
//			    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//			    table.addCell(verticalAlignCell);
			}
	
	
}
