/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.PrinterClass;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import model.Tools;

/**
 *
 * @author Isaac
 */
public class PrinterClass {
    public static String DEFAULT_OUTPUT = "c:/Users/Isaac/Desktop/iText.pdf";
    public String title = " List of Suppliers";
    com.itextpdf.text.Font greenFont = FontFactory.getFont(FontFactory.defaultEncoding, 12, com.itextpdf.text.Font.UNDERLINE,BaseColor.BLACK);
    private ArrayList<String> columnNames;
    private ArrayList<String> data;
    String output = null;
    
    public PrinterClass(String title,ArrayList<String> columnNames, ArrayList<String> data){
        this.title= title;
        this.data = data;
        this.columnNames = columnNames;
    }
    
    public void changeOutput(String path){
        output = path;
    }
    
     public String printToPDF() throws FileNotFoundException, DocumentException, IOException{
    //Initialise
        Tools.print("Printing start...\n");
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(output==null?DEFAULT_OUTPUT:output));
        
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        writer.setPageEvent(event);
        
        document.open();
                /************* Titre *****************/
       Paragraph first = new Paragraph("   ");
       first.setSpacingAfter(70);
       document.add(first);
       
       Paragraph titre = new Paragraph(this.title,greenFont);
       titre.setAlignment(Element.ALIGN_CENTER);
       titre.setSpacingAfter(20);
       document.add(titre);
        
            /************ Content **************/
            int size = columnNames.size();
        PdfPTable content = new PdfPTable(size); 
        content.setWidthPercentage(100);
        float[]  contentWidth = new float[size];
        for(int i=0;i<size;i++){contentWidth[i]= 1f;}
        content.setWidths(contentWidth);

        for(String value: columnNames){
             PdfPCell name = new PdfPCell(new Paragraph(value));
             name.setHorizontalAlignment(Element.ALIGN_CENTER);
             name.setVerticalAlignment(Element.ALIGN_MIDDLE);
             content.addCell(name);
        }

        data.forEach((value) -> {
            content.addCell(value);
        });

        document.add(content);
        
        //Set Attributes
        document.addAuthor("Script Farm");
        document.addCreationDate();
        document.addCreator("Script Farm");
        document.addTitle(title);
        document.addSubject("Farm Activities "+title);
        
        //Close
        //writer.close();
        document.close();
        Tools.print("Printing ended\n");
        return output==null?DEFAULT_OUTPUT:output;
    }
     
}
