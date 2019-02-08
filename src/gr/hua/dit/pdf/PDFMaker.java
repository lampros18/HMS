package gr.hua.dit.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import gr.hua.dit.entity.HousingApplication;
import gr.hua.dit.entity.Student;

public class PDFMaker {

	private Document document;
	private PdfPTable housingApplicationTable;
	private PdfPTable userTable;
	private BaseFont cellBaseFont;
	private Font cellFont;

	public byte[] init(HousingApplication ha)
			throws MalformedURLException, IOException, URISyntaxException, DocumentException {
		document = new Document(PageSize.A4);
		document.setMargins(40, 40, document.topMargin(), document.bottomMargin());


		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
		document.open();

		document.addTitle("Housing Application");
//---------------------------------------------------------------
		BaseFont fonty = BaseFont.createFont(this.getClass().getResource("Hack-Bold.ttf").toURI().toString(),
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		fonty.setSubset(true);
		Font myfonty = new Font(fonty, 16, Font.NORMAL);

		Paragraph paragraph = new Paragraph("ΑΙΤΗΣΗ ΣΤΕΓΑΣΗΣ", myfonty);
		paragraph.setAlignment(Element.ALIGN_CENTER);

//-------------------------------------------------------------------
		document.add(paragraph);
		document.add(Chunk.NEWLINE);

//---------------------------------------------------------------
		cellBaseFont = BaseFont.createFont(this.getClass().getResource("Hack-Regular.ttf").toURI().toString(),
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		cellBaseFont.setSubset(true);
		cellFont = new Font(cellBaseFont, 11, Font.NORMAL);

//-------------------------------------------------------------------
		userTable = new PdfPTable(2);
		userTable.setWidthPercentage(40);
		userTable.setHorizontalAlignment(Element.ALIGN_LEFT);
		setUserData(ha.getStudent());
		document.add(userTable);
		document.add(Chunk.NEWLINE);
//-------------------------------------------------------------------
		housingApplicationTable = new PdfPTable(7);
		housingApplicationTable.setWidthPercentage(100);
		housingApplicationTable.setTotalWidth(145);
		housingApplicationTable.setWidths(new float[] { 32, 17, 15, 24, 15, 19, 23 });
		addTableHeader(housingApplicationTable);
		setHousingApplicationData(ha);

		try {
			document.add(housingApplicationTable);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		addBackgroundLogoImage("hua_logo.png");
		document.newPage();
		addCustomFile(ha, writer);
		document.close();
		return byteArrayOutputStream.toByteArray();
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Φοιτητής", "Εισόδημα", "Αριθμός ανέργων γονέων", "Οικογενειακό εισόδημα", "Αδέρφια που σπουδά-ζουν",
				"Κατάγεται απο πόλη φοίτησης", "Ημ/νία δημιουργίας").forEach(columnTitle -> {
					PdfPCell header = new PdfPCell();
					header.setBackgroundColor(BaseColor.LIGHT_GRAY);
					header.setBorderWidth(2);
					header.setPhrase(new Phrase(columnTitle, cellFont));
					table.addCell(header);
				});
	}

	private void addRows(PdfPTable table, HousingApplication ha) {
		table.addCell(pdfPCell(ha.getStudent().getUser().getUsername()));
		table.addCell(pdfPCell(String.valueOf(ha.getPersonalIncome())));
		table.addCell(pdfPCell(String.valueOf(ha.getUnemployedParents())));
		table.addCell(pdfPCell(String.valueOf(ha.getFamilyIncome())));
		table.addCell(pdfPCell(String.valueOf(ha.getNumberOfStudentSiblings())));
		table.addCell(pdfPCell(ha.isLocalResident() == 1 ? "Ναι" : "Όχι"));
		table.addCell(pdfPCell(ha.getCreated_at()));
	}

	private void addRow(PdfPTable table, String key, String value) {
		table.addCell(pdfPCell(key));
		table.addCell(pdfPCell(value));
	}

	private void addBackgroundLogoImage(String image)
			throws URISyntaxException, MalformedURLException, IOException, DocumentException {
		Path path = Paths.get(this.getClass().getResource(image).toURI());
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		float x = (PageSize.A4.getWidth() - img.getScaledWidth()) / 2;
		float y = (PageSize.A4.getHeight() - img.getScaledHeight()) / 2;
		img.setAbsolutePosition(x, y);
		document.add(img);
	}

	private void addCustomFile(HousingApplication ha, PdfWriter writer) throws URISyntaxException, MalformedURLException, IOException, DocumentException {
		if(ha.getFileType().equals("application/pdf")) {
            // Copyright: http://burnignorance.com/java-web-development-tips/java-merging-multiple-pdfs-into-a-single-pdf-using-itext/
			PdfReader reader = new PdfReader(ha.getFile());
            PdfContentByte cb = writer.getDirectContent();
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                document.newPage();
                //import the page from source pdf
                PdfImportedPage page = writer.getImportedPage(reader, i);
                //add the page to the destination pdf
                cb.addTemplate(page, 0, 0);
            }
		} else {
			document.newPage();
			Image img = Image.getInstance(ha.getFile());
			float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin())
					/ img.getWidth()) * 100;
			img.scalePercent(scaler);
			document.add(img);
		}
	}

	private void setUserData(Student student) {
		addRow(userTable, "Όνομα: ", student.getName());
		addRow(userTable, "Επώνυμο: ", student.getSurname());
		addRow(userTable, "Ημ/νία Γέννησης: ", student.getBirthdate());
		addRow(userTable, "Έτος εγγραφής: ", String.valueOf(student.getYearOfEnrollment()));
		addRow(userTable, "Μεταπτυχιακός: ", student.isPostgraduate() ? "Ναι" : "Όχι");
		addRow(userTable, "Τμήμα: ", student.getDepartment());
		addRow(userTable, "Τηλέφωνο: ", student.getPhone());
		addRow(userTable, "Διεύθυνση: ", student.getAddress());
	}

	private void setHousingApplicationData(HousingApplication ha) {
		addRows(housingApplicationTable, ha);
	}

	private PdfPCell pdfPCell(String value) {
		return new PdfPCell(new Phrase(value, cellFont));
	}
}
