/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doit.jin;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Test {

   public static void main(String [] args) {    
       System.out.println("====================================");
       System.out.println(" java test  mail_destino  mail_origen  IP_SMTP  user_SMTP password_SMTP");
       System.out.println("====================================");
      // Recipient's email ID needs to be mentioned.
      String to = args[0];

      // Sender's email ID needs to be mentioned
      String from = args[1];

      // Assuming you are sending email from localhost
      String host = args[2];

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      
      // Creadenciales
      properties.setProperty("mail.user", args[3]);
      properties.setProperty("mail.password", args[4]);      

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Mensaje de prueba SMTP");

         // Now set the actual message
         message.setText("Esta es una prueba para verificar el funcionamiento del SMTP server ");

         // Send message
         Transport.send(message);
         System.out.println("Mensaje enviado ....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}