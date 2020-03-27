/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.PrinterClass;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import controller.PdfViewerController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import model.Tools;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 *
 * @author Isaac
 */
public class PdfViewer {
    private String pdfToLoad=null;
    
    public PdfViewer(String v){
        this.pdfToLoad =v;
    }
    
    
   private Image loadImage() throws FileNotFoundException, DocumentException, IOException{
        Document doc = new Document();
        PdfWriter writer=null;
        try {
            writer = PdfWriter.getInstance(doc, new FileOutputStream(pdfToLoad));
        } catch (FileNotFoundException ex) {
           Tools.print("Writer :Unable to find " + pdfToLoad);
        } catch (DocumentException ex) {
            Logger.getLogger(PdfViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PdfReader reader=null;
        try {
            reader = new PdfReader(pdfToLoad);
        } catch (IOException ex) {
           Tools.print("Reader :Unable to find " + pdfToLoad);
        }
        short i =0;
        Tools.print("Generating image start...");
      //  if(reader.getNumberOfPages()>1){
             Document tmp = new Document();
             PdfWriter writer2 = PdfWriter.getInstance(tmp, new FileOutputStream("tmp.pdf"));
             com.itextpdf.text.Image imgTemp= com.itextpdf.text.Image.getInstance(writer.getImportedPage(reader, 1));
             tmp.add(imgTemp);
             tmp.close();
             i=1;
      //  }
        doc.close();
        
        //PDDocument document = PDDocument.load(new File((i==0)?pdfToLoad:"tmp.pdf"));  
        PDDocument document = null;
        try{
              document = PDDocument.load(new File("tmp.pdf"));   
        } catch (IOException ex) {
           Tools.print("PDDocument :Unable to find " + pdfToLoad);
        }
        PDFRenderer ren =new PDFRenderer(document);
        BufferedImage img = ren.renderImageWithDPI(0, 300,ImageType.RGB);
        ImageIO.write(img, "png",new File("test.png"));
        document.close();
        Tools.print("Generating image ended ...");
        
        return new Image("test.png");
   }
    
    
    public void show()
    {
       Parent dialog;
       FXMLLoader loader;
         try {
            loader = new FXMLLoader(getClass().getResource("/view/pdfViewer.fxml"));
            dialog = loader.load();
            PdfViewerController con = loader.getController();
            try{
              con.loadImage(loadImage());
            }catch (IOException ex) {
             Tools.print("Image failed");
            } 
           
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(dialog));
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (IOException ex) {
            Tools.print("Interface not found");
        } catch (DocumentException ex) {
            Tools.print("Image failed to load");
        }
    }
    
    
    public void show(String value){
        this.pdfToLoad = value;
        show();
    }
}
