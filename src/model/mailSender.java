 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Isaac
 */
public interface mailSender {    
    
    public static Boolean send(String _to , String _code)
    {
        Mail mail = new Mail("bekolleisaac@gmail.com","scriptdrive01");
        String[] toAddress = {_to};
        mail.setTo(toAddress);
        mail.setBody("<h1>Your verification code is:"+_code+"</h1>");
        mail.setSubject("Script-Farm: Verification Code");        
        System.out.println("to="+((String[])mail.getTo())[0]+" code ="+_code+" subject="+mail.getSubject()+" body="+mail.getBody());
        return mail.send();
    }
}
