/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *Allow less secure apps from https://myaccount.google.com/lesssecureapps
 * @author Isaac
 */ 

public class Mail extends javax.mail.Authenticator { 
 private String _user; 
 private String _pass; 

 private String[] _to; 
 private String _from; 

 private String _port; 
 private String _sport; 

 private String _host; 

 private String _subject; 
 private String _body; 

 private boolean _auth; 

 private boolean _debuggable; 

 private Multipart _multipart; 


 public Mail() { 
 _host = "smtp.gmail.com"; // default smtp server 
 _port = "465"; // default smtp port 
 _sport = "465"; // default socketfactory port 

 _user = ""; // username 
 _pass = ""; // password 
 _from = ""; // email sent from 
 _subject = ""; // email subject 
 _body = ""; // email body 

 _debuggable = false; // debug mode on or off - default off 
 _auth = true; // smtp authentication - default on 

 _multipart = new MimeMultipart(); 

 // There is something wrong with MailCap, javamail can not find a handler for the  
 //multipart/mixed part, so this bit needs to be added. 
 MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap(); 
 mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html"); 
 mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml"); 
 mc.addMailcap("text/plain;; x-java-content- handler=com.sun.mail.handlers.text_plain"); 
 mc.addMailcap("multipart/*;; x-java-content- handler=com.sun.mail.handlers.multipart_mixed"); 
 mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822"); 
 CommandMap.setDefaultCommandMap(mc); 
 } 

 public Mail(String user, String pass) { 
 this(); 
 _user = user; 
 _from = user;
 _pass = pass; 
} 

 public boolean send(){ 
 Properties props = _setProperties(); 

 if( (!_user.equals("")) && (!_pass.equals("")) && (_to.length > 0) && (!_from.equals("")) && (!_subject.equals("")) && (!_body.equals("")) )
 { 
     try {
         Session session = Session.getInstance(props, this);
         
         MimeMessage msg = new MimeMessage(session);
         
         msg.setFrom(new InternetAddress(_from));
         
         InternetAddress[] addressTo = new InternetAddress[_to.length];
         for (int i = 0; i < _to.length; i++) {
             addressTo[i] = new InternetAddress(_to[i]);
         }
         msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);
         
         msg.setSubject(_subject);
         msg.setSentDate(new Date());
         
         // setup message body
         BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(_body,"text/html");   
        // messageBodyPart.setText(_body);
         _multipart.addBodyPart(messageBodyPart);
         
         // Put parts in message
         msg.setContent(_multipart);
         
         // send email
         Transport.send(msg); 
         Tools.showNotification("Mail Sender", "Verification code send successfully to "+ _to[0], Boolean.FALSE,Pos.TOP_RIGHT,5);
         return true;
     } catch (AddressException ex) {
        /// Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        Tools.showNotification("Mail Sender", "Sending Verification code failed. Please report to "+_from+" and try again", Boolean.TRUE,Pos.TOP_RIGHT,10);
     } catch (MessagingException ex) {
       //  Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
       Tools.showNotification("Mail Sender", "Sending Verification code failed. Please report to "+_from+" and try again", Boolean.TRUE,Pos.TOP_RIGHT,10);
     }
  } else{ 
     Tools.showNotification("Mail Sender", "Sending Verification code failed. Please report to "+_from+" and try again", Boolean.TRUE,Pos.TOP_RIGHT,10);
   } 
   return false;
  } 

  public void addAttachment(String filename) throws Exception { 
  BodyPart messageBodyPart = new MimeBodyPart(); 
  DataSource source = new FileDataSource(filename);
  messageBodyPart.setDataHandler(new DataHandler(source)); 
  messageBodyPart.setFileName(filename); 

  _multipart.addBodyPart(messageBodyPart); 
  } 

  @Override 
  public PasswordAuthentication getPasswordAuthentication() { 
  return new PasswordAuthentication(_user, _pass); 
  } 

  private Properties _setProperties() { 
  Properties props = new Properties(); 

  props.put("mail.smtp.host", _host); 

  if(_debuggable) { 
  props.put("mail.debug", "true"); 
  } 

  if(_auth) { 
  props.put("mail.smtp.auth", "true"); 
  } 

  props.put("mail.smtp.port", _port); 
  props.put("mail.smtp.socketFactory.port", _sport); 
  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
  props.put("mail.smtp.socketFactory.fallback", "false"); 

  return props; 
  } 

  // the getters and setters 
 public String getBody() { 
 return _body; 
 } 

 public void setBody(String _body) { 
 this._body = _body; 
 }

  public void setTo(String[] toArr) {
  // TODO Auto-generated method stub
  this._to=toArr;
 }

  public void setFrom(String string) {
  // TODO Auto-generated method stub
  this._from=string;
 }

  public void setSubject(String string) {
  // TODO Auto-generated method stub
  this._subject=string;
  }

  public String getSubject() {
  // TODO Auto-generated method stub
  return this._subject;
  }
  
  public String[] getTo() {
// TODO Auto-generated method stub
    return _to;
  }  

 } 