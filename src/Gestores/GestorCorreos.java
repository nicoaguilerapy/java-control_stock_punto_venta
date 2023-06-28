package Gestores;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class GestorCorreos extends Thread {
    
    private Properties propiedad = new Properties();
    private String email = "", password = "", emailTo = "", asunto = "", PATH = "";
    
    ;

    public GestorCorreos() {
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
    }
    
    @Override
    public void run() {
        if (!password.equals("")) {
            if (sendEmailPDF()) {
                JOptionPane.showMessageDialog(null, "Correo Enviado correctamente", "Enviar Correo", JOptionPane.INFORMATION_MESSAGE);
                File FILE = new File(PATH);
                FILE.delete();
            }
        }
        
        yield();
    }
    
    public boolean sendEmail(String destino, String asuntoAux) {
        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = email;
        String contrasena = password;
        String receptor = destino;
        String asunto = asuntoAux;
        String mensaje = "Correo de prueba";
        
        MimeMessage mail = new MimeMessage(sesion);
        
        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia, contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transportar.close();
            
            return true;
            
        } catch (HeadlessException | MessagingException e) {
        }
        
        return false;
        
    }
    
    public boolean sendEmailPDF() {
        System.out.println("--------------------");
        System.out.println("Gestores.GestorCorreos.sendEmailPDF()");
        System.out.println("Configuracion: email: " + email + " | password: " + password);
        System.out.println(PATH);
        System.out.println(emailTo);
        System.out.println(asunto);
        System.out.println("--------------------");
        Session sesion = Session.getDefaultInstance(propiedad);
        File FILE = null;
        
        MimeMessage mail = new MimeMessage(sesion);
        
        try {
            mail.setFrom(new InternetAddress(email));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mail.setSubject(asunto);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("");
            
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(PATH));
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            mail.setContent(multipart);
            
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(email, password);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transportar.close();
            
            return true;
            
        } catch (HeadlessException | MessagingException e) {
            JOptionPane.showMessageDialog(null, "Error de Correo en Remitente", "Enviar Correo", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de archivo", "Enviar Correo", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmailTo() {
        return emailTo;
    }
    
    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
    
    public String getAsunto() {
        return asunto;
    }
    
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    public String getPATH() {
        return PATH;
    }
    
    public void setPATH(String PATH) {
        this.PATH = PATH;
    }
    
}
