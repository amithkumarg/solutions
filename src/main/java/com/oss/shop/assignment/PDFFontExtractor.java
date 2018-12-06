package com.oss.shop.assignment;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFFontExtractor extends PDFTextStripper {

	public PDFFontExtractor() throws IOException {
		super();
	}

	public static void main(String[] args) {

		try (PDDocument document = PDDocument.load(new File("/home/neha/Downloads/docs/General.pdf"))) {
			PDFFontExtractor textStripper = new PDFFontExtractor();
			textStripper.setSortByPosition(true);
			for (int page = 1; page <= document.getNumberOfPages(); page++) {
				textStripper.setStartPage(page);
				textStripper.setEndPage(page);
				String pdfFileText = textStripper.getText(document);
				// split by line
				String lines[] = pdfFileText.split("\\n");
				for (int line = 0; line < lines.length; line++) {
					System.out.println(String.format("Page: %s, Line: %s, Text: %s", page, line, lines[line]));
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
