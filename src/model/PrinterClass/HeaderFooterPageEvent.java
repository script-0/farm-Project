/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.PrinterClass;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isaac
 */
public class HeaderFooterPageEvent extends PdfPageEventHelper{
    private PdfTemplate template;
    private Image total;
    public String LOGO = "E:/Academie/personal_documents/codes/Java/ScriptFarm/src/images/logo2.png";
    
    @Override
    public void onOpenDocument(PdfWriter writer,Document doc){
        template = writer.getDirectContent().createTemplate(30, 16);
        try{
            total =  Image.getInstance(template);
            total.setRole(PdfName.ARTIFACT);
            
        } catch (BadElementException ex) {
            Logger.getLogger(HeaderFooterPageEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void onStartPage(PdfWriter writer, Document doc){
        addHeader(writer);
        addFooter(writer);
    }
    
    public void addHeader(PdfWriter writer){
        try {
            /*** Entete du PDF  **/
            PdfPTable entete = new PdfPTable(3);
            //entete.setWidthPercentage(90);
            //entete.getDefaultCell().setFixedHeight(50);
            float[] columnWidth = {1f,1f,1f};
            entete.setWidths(columnWidth);
            entete.setTotalWidth(525);
            entete.setLockedWidth(true);
            entete.getDefaultCell().setFixedHeight(42);
           
            //Logo
            Image logo = Image.getInstance(LOGO);
            logo.scaleAbsolute(40, 40);
            logo.setAlignment(Element.ALIGN_CENTER);           
            //Description
            Paragraph title = new Paragraph();
            Phrase nom =  new Phrase("------- Script Farm -------\n");
            Phrase text = new Phrase("Farm activities manager\n");
            Phrase team = new Phrase("@ScriptFarm_Team");
            
            title.add(nom);
            title.add(text);
            title.add(team);
            
            
            PdfPCell left = new PdfPCell(title);
            left.setHorizontalAlignment(Element.ALIGN_CENTER);
            left.setVerticalAlignment(Element.ALIGN_MIDDLE);
            left.setPaddingBottom(8);
            left.setBorder(Rectangle.BOTTOM);
            left.setBorderColor(BaseColor.LIGHT_GRAY);
            
            PdfPCell center = new PdfPCell(logo);
            center.setHorizontalAlignment(Element.ALIGN_CENTER);
            center.setBorder(Rectangle.BOTTOM);
            center.setBorderColor(BaseColor.LIGHT_GRAY);
            
            //Date
            SimpleDateFormat day = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            Paragraph date = new Paragraph(day.format(new Date()));
            PdfPCell right = new PdfPCell(date);
            right.setHorizontalAlignment(Element.ALIGN_CENTER);
            right.setVerticalAlignment(Element.ALIGN_MIDDLE);
            right.setBorder(Rectangle.BOTTOM);
            right.setBorderColor(BaseColor.LIGHT_GRAY);
            
            
            entete.addCell(left);
            entete.addCell(center);
            entete.addCell(right);
            
            entete.writeSelectedRows(0, -1,34,800, writer.getDirectContent());
        } catch (BadElementException ex) {
            Logger.getLogger(HeaderFooterPageEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(HeaderFooterPageEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addFooter(PdfWriter writer){
        PdfPTable footer = new PdfPTable(4);
        try{
            footer.setWidths(new float[]{3,2.5f,2,0.5f});
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
            footer.setTotalWidth(525);
            SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
            footer.addCell(new Phrase("Generated at : "+ hour.format(new Date())));
            
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            footer.addCell(new Phrase("\u00A9 Script_Farm_Team",new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
            
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(new Phrase(String.format("Page %d of ", writer.getPageNumber()),new Font(Font.FontFamily.HELVETICA,11)));
            
            PdfPCell cell = new PdfPCell(total);
            cell.setPaddingTop(3);
            cell.setBorder(Rectangle.TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            footer.addCell(cell);
            
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch (DocumentException ex) {
            Logger.getLogger(HeaderFooterPageEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void onCloseDocument(PdfWriter writer, Document doc){
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 6;
        ColumnText.showTextAligned(template, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber())),totalWidth, 6, 0);
    }
}
